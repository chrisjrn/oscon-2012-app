import icalendar
import sys
import json
import re
import contextlib


def main():
    ical_text = sys.stdin.read()
    with swallow_stdout(open("/dev/null")):
        cal = icalendar.cal.Calendar.from_ical(ical_text)
    
    events = []
    
    for event in cal.walk("VEVENT"):
        #print extract_author(event["DESCRIPTION"])[0]
        #pass
        #print event
                
        author, description = extract_author(event["DESCRIPTION"])
        start = str(event["DTSTART"].dt)
        end = str(event["DTEND"].dt)        
        jevent = {
            "author": author,
            "title": event["SUMMARY"],
            "description": description,
            "room": event["LOCATION"],
            "raw_description" : event["DESCRIPTION"],
            "start" : start,
            "end" : end,
            "url" : event["URL"],
            
        }
        events.append(jevent)
        
    json_out = json.dumps(events, indent=2)
    
    print json_out
        

@contextlib.contextmanager
def swallow_stdout(handle):
    old_stdout = sys.stdout
    sys.stdout = handle
    yield
    sys.stdout = old_stdout

PATTERN_THINGY = r'Presented by( (.*?\(.*?\))\.) (.*)'
#PATTERN_THINGY = r'Presented by( (.*?\(.*?\),)*?(.*?\(.*?\))\.)(.*)'
def extract_author(description):
    try:
        return re.search(PATTERN_THINGY, description).groups()[1:3]
    except AttributeError:
        #print >> sys.stderr, description
        #print description
        return ("", description)
    
if __name__ == "__main__":
    main()
    