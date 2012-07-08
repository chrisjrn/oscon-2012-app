package org.s31.oscon2013;

import java.util.ArrayList;
import java.util.List;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ScheduleFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		int timeSlot = getArguments().getInt("time_slot", 0);
		TimeSlot t = Schedule.mTimeSlots.get(timeSlot);
		Log.v("ScheduleFragment", "Loading timeSlot: " + timeSlot);
		List<Event> events = Schedule.eventsInDateRange(t.start, t.end);

		ScheduleAdapter a = new ScheduleAdapter(getActivity(), 0, events);
		setListAdapter(a);

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getListView().setOnItemClickListener(new OnItemClickListener() {

			// The names are the defaults from Eclipse, unfortunately they are:
			// arg0 - the ListView
			// arg1 - the view for the list item
			// arg2 - the position that was touched
			// arg3 - the android resource ID for the given item
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				showTalkListing(arg2);
			}

		});
	}

	public void showTalkListing(int position) {
		Intent i = new Intent(getActivity(), TalkListingActivity.class);
		int id = ((Event) getListAdapter().getItem(position)).id;
		i.putExtra("event", id);
		getActivity().startActivity(i);
	}

	// Ignore the man behind the curtain :)
	public List<Event> getHardcodedEventsList() {
		List<Event> l = new ArrayList<Event>();
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"Games account for about half of the apps in the typical app store and are the first thing ported to any new platform. This year's edition of the popular HTML Canvas Deep Dive will focus on building cross-platform games. We will cover everything needed for basic games with animation, audio, image loading, sprites, and joystick support, then package them for desktop, mobile web, and app stores.",
				"Joshua Marinacci (Nokia)",
				"Presented by Joshua Marinacci (Nokia). Games account for about half of the apps in the typical app store and are the first thing ported to any new platform. This year's edition of the popular HTML Canvas Deep Dive will focus on building cross-platform games. We will cover everything needed for basic games with animation, audio, image loading, sprites, and joystick support, then package them for desktop, mobile web, and app stores.",
				"HTML Canvas Deep Dive", "Portland 251",
				"http://www.oscon.com/oscon2013/public/schedule/detail/28823"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"In this session you'll learn why you can't consider UX and design an optional extra when designing mobile apps, and how to tell an awesome app from a terrible app. In this platform-agnostic design-heavy workshop, you'll learn how to build wireframes, how to translate those wireframes into actual working Android code, and how to evaluate your designs for future improvement.",
				"Paris Buttfield-Addison (Secret Lab Pty. Ltd.), Christopher Neugebauer (chris.neugebauer.id.au), Jonathon Manning (Secret Lab Pty. Ltd.)",
				"Presented by Paris Buttfield-Addison (Secret Lab Pty. Ltd.), Christopher Neugebauer (chris.neugebauer.id.au), Jonathon Manning (Secret Lab Pty. Ltd.). In this session you'll learn why you can't consider UX and design an optional extra when designing mobile apps, and how to tell an awesome app from a terrible app. In this platform-agnostic design-heavy workshop, you'll learn how to build wireframes, how to translate those wireframes into actual working Android code, and how to evaluate your designs for future improvement.",
				"Level Up Your Apps: Mobile UX Design and Development",
				"Portland 252",
				"http://www.oscon.com/oscon2013/public/schedule/detail/29002"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"One of the most popular configuration and cloud management tools, Chef is a powerful platform for rapid provisioning and deployment of servers. Attend this tutorial to learn  what benefits Chef can bring, how to get started and best use Chef to meet your needs.",
				"Joshua Timberman (Opscode, Inc), James Casey (Opscode, Inc.)",
				"Presented by Joshua Timberman (Opscode, Inc), James Casey (Opscode, Inc.). One of the most popular configuration and cloud management tools, Chef is a powerful platform for rapid provisioning and deployment of servers. Attend this tutorial to learn  what benefits Chef can bring, how to get started and best use Chef to meet your needs.",
				"Systems Management with Chef", "Portland 255",
				"http://www.oscon.com/oscon2013/public/schedule/detail/29621"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"Apache Solr is a Lucene-based blazing fast, highly scalable search engine used in thousands of applications and projects at organizations such as Zappos, Wells Fargo, Getty Images and many more. This tutorial will provide you with the fundamentals, enabling you to be up and running with Solr in minutes.",
				"Erik Hatcher (LucidWorks)",
				"Presented by Erik Hatcher (LucidWorks). Apache Solr is a Lucene-based blazing fast, highly scalable search engine used in thousands of applications and projects at organizations such as Zappos, Wells Fargo, Getty Images and many more. This tutorial will provide you with the fundamentals, enabling you to be up and running with Solr in minutes.",
				"Solr Quick Start", "Portland 256",
				"http://www.oscon.com/oscon2013/public/schedule/detail/28933"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"This tutorial gives you the details you need to become an idiomatic PHP programmer when you're coming from another language. We won't waste time on basics but instead tour the landscape of the \"PHP way\" to approach tasks and concepts that you already know how to do in another language, such as wrangling types, OO, errors, performance, external packages, and development environment.",
				"David Sklar (Ning (Glam Media))",
				"Presented by David Sklar (Ning (Glam Media)). This tutorial gives you the details you need to become an idiomatic PHP programmer when you're coming from another language. We won't waste time on basics but instead tour the landscape of the \"PHP way\" to approach tasks and concepts that you already know how to do in another language, such as wrangling types, OO, errors, performance, external packages, and development environment.",
				"Writing Idiomatic PHP", "D136",
				"http://www.oscon.com/oscon2013/public/schedule/detail/28834"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"An extensive look at simple, practical, concrete methods to make your website or webapp more accessible for people with disabilities and in all forms of assistive technology. Participants will leave with a number of tips, tricks, and tools they can use on any site, no matter how simple or fancy it is -- and a number of examples of doing it incorrectly.",
				"Denise Paolucci (Dreamwidth Studios), Deborah Kaplan (Dreamwidth Studios)",
				"Presented by Denise Paolucci (Dreamwidth Studios), Deborah Kaplan (Dreamwidth Studios). An extensive look at simple, practical, concrete methods to make your website or webapp more accessible for people with disabilities and in all forms of assistive technology. Participants will leave with a number of tips, tricks, and tools they can use on any site, no matter how simple or fancy it is -- and a number of examples of doing it incorrectly.",
				"Web Accessibility for the 21st Century", "D137/138",
				"http://www.oscon.com/oscon2013/public/schedule/detail/28801"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"A fun, interactive, and comprehensive tutorial on how to host a successful code sprint, hackathon, (un)conference or workshop.",
				"Christie Koehler (Mozilla / Stumptown Syndicate), Audrey Eschright (Elevated Code / Stumptown Syndicate), Sherri Montgomery (Open Source Bridge / Ignite Portland / Mentor Graphics)",
				"Presented by Christie Koehler (Mozilla / Stumptown Syndicate), Audrey Eschright (Elevated Code / Stumptown Syndicate), Sherri Montgomery (Open Source Bridge / Ignite Portland / Mentor Graphics). A fun, interactive, and comprehensive tutorial on how to host a successful code sprint, hackathon, (un)conference or workshop.",
				"Community-Driven Event Planning", "D139/140",
				"http://www.oscon.com/oscon2013/public/schedule/detail/29259"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"In this session we'll quickly go over the basic concepts of juju and spend the rest of the time walking through explicit examples of juju in action.  We'll look at stacks of services and the charms behind those services.",
				"Jorge Castro (Canonical Ltd. / Ubuntu), Mark Mims (Canonical Ltd / Ubuntu)",
				"Presented by Jorge Castro (Canonical Ltd. / Ubuntu), Mark Mims (Canonical Ltd / Ubuntu). In this session we'll quickly go over the basic concepts of juju and spend the rest of the time walking through explicit examples of juju in action.  We'll look at stacks of services and the charms behind those services.",
				"Service Orchestration In The Cloud With Juju", "E143/144",
				"http://www.oscon.com/oscon2013/public/schedule/detail/29115"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"This hands-on workshop will walk you through building a simple distributed sensor network. Using an Arduino board, off-the-shelf sensors, and XBee radios, we'll show you how to put together an individual sensor platform (commonly known as a \"mote\") and how to network more than one of these platforms together to build a small scale distributed network.",
				"Alasdair Allan (Babilim Light Industries), Kipp Bradford (Kippworks)",
				"Presented by Alasdair Allan (Babilim Light Industries), Kipp Bradford (Kippworks). This hands-on workshop will walk you through building a simple distributed sensor network. Using an Arduino board, off-the-shelf sensors, and XBee radios, we'll show you how to put together an individual sensor platform (commonly known as a \"mote\") and how to network more than one of these platforms together to build a small scale distributed network.",
				"Building A Distributed Sensor Network", "E145/146",
				"http://www.oscon.com/oscon2013/public/schedule/detail/29622"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"Presented by leaders of multiple open source non-profit foundations, this session introduces choices of governance and organisation for those considering anchoring their community with a non-profit organisation.",
				"Simon  Phipps (Open Source Initiative), Josh Berkus (PostgreSQL Experts, Inc.), Steve Holden (Holden Web LLC), Paula Hunter (Outercurve Foundation), Bradley Kuhn (Software Freedom Conservancy), Dave Neary (Red Hat), Deb Nicholson (Open Invention Network), Cedric  Thomas (OW2), Ian Skerrett (Eclipse Foundation)",
				"Presented by Simon  Phipps (Open Source Initiative), Josh Berkus (PostgreSQL Experts, Inc.), Steve Holden (Holden Web LLC), Paula Hunter (Outercurve Foundation), Bradley Kuhn (Software Freedom Conservancy), Dave Neary (Red Hat), Deb Nicholson (Open Invention Network), Cedric  Thomas (OW2), Ian Skerrett (Eclipse Foundation). Presented by leaders of multiple open source non-profit foundations, this session introduces choices of governance and organisation for those considering anchoring their community with a non-profit organisation.",
				"Community Foundations 101", "E147",
				"http://www.oscon.com/oscon2013/public/schedule/detail/29249"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"Clojure is the most interesting new language on the JVM, both from a syntactic and capabilities standpoint. This workshop teaches attendees Clojure syntax, Java interoperability, and how to build applications, both Swing and Web, using Clojure.",
				"Neal Ford (ThoughtWorks)",
				"Presented by Neal Ford (ThoughtWorks). Clojure is the most interesting new language on the JVM, both from a syntactic and capabilities standpoint. This workshop teaches attendees Clojure syntax, Java interoperability, and how to build applications, both Swing and Web, using Clojure.",
				"Introduction to Clojure", "F150",
				"http://www.oscon.com/oscon2013/public/schedule/detail/28779"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 17:00:00-07:00",
				"inBloom releases open-source for a core educational service at OSCon 2013. Released under the Apache 2 license, come learn how to use this service in your apps and contribute to improving the core search service for usage in school districts across the nation.",
				"Jason Hoekstra  (inBloom)",
				"Presented by Jason Hoekstra  (inBloom). inBloom releases open-source for a core educational service at OSCon 2013. Released under the Apache 2 license, come learn how to use this service in your apps and contribute to improving the core search service for usage in school districts across the nation.",
				"Code-a-Thon / inBloom LRI -- Learning Resource Discovery Engine",
				"D 135",
				"http://www.oscon.com/oscon2013/public/schedule/detail/31509"));
		l.add(new Event(
				"2013-07-23 09:00:00-07:00",
				"2013-07-23 12:30:00-07:00",
				"The Rackspace Unlocked team will cover the Five Pillars of Cloudiness, the five key tenets that dive into how to navigate uncharted territory and design cloud applications. Learn how public, private and hybrid clouds can be leveraged to your advantage to free your application(s) from a one-size-fits-all cloud in favor of one that is a perfect fit.",
				"Wayne Walls (Rackspace Hosting), Alex Brandt  (Rackspace Hosting)",
				"Presented by Wayne Walls (Rackspace Hosting), Alex Brandt  (Rackspace Hosting). The Rackspace Unlocked team will cover the Five Pillars of Cloudiness, the five key tenets that dive into how to navigate uncharted territory and design cloud applications. Learn how public, private and hybrid clouds can be leveraged to your advantage to free your application(s) from a one-size-fits-all cloud in favor of one that is a perfect fit.",
				"'Butter' Up Your Application", "E142",
				"http://www.oscon.com/oscon2013/public/schedule/detail/31425"));
		return l;
	}
}
