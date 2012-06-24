import json
import sys


def main():
    text = json.load(sys.stdin)
    
    print "<html><body><table border='1px'>"
    
    for event in text:
        print "<tr><td>"
        
        r, a, d = event["raw_description"], event["author"], event["description"]
        
        print event["raw_description"].replace(
            a, u"<span style='color: #FF0000'>" + a + "</span>").replace(
            d, u"<span style='color: #0000FF'>" + d + "</span>").encode("UTF-8")
        
        print "</tr></td>"
        
    print "</table></body></html>"
    

if __name__ == "__main__":
    main()
    
    