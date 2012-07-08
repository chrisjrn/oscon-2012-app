package org.s31.oscon2012;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ScheduleFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ScheduleAdapter a = new ScheduleAdapter(getActivity(), 0, getHardcodedEventsList());
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
		getActivity().startActivity(i);
	}


	// Ignore the man behind the curtain :)
	public List<Event> getHardcodedEventsList() {
		List<Event> l = new ArrayList<Event>();
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"So you know the basics of jQuery and Selectors, but you want to solidify your knowledge with jQuery events, ajax, effects, and code organization. This course picks up where Intro to jQuery 1 left off, jumping straight into the most useful jQuery techniques. We'll cover Events, AJAX, Effects, and Code Organization in detail, utilizing the Code School engine so you can code in the browser.",
				"Gregg Pollack (Envy Labs), Olivier Lacan (Envy Labs), Christopher Green (Envy Labs), Tyler Hunt (Envy Labs)",
				"Presented by Gregg Pollack (Envy Labs), Olivier Lacan (Envy Labs), Christopher Green (Envy Labs), Tyler Hunt (Envy Labs). So you know the basics of jQuery and Selectors, but you want to solidify your knowledge with jQuery events, ajax, effects, and code organization. This course picks up where Intro to jQuery 1 left off, jumping straight into the most useful jQuery techniques. We'll cover Events, AJAX, Effects, and Code Organization in detail, utilizing the Code School engine so you can code in the browser.",
				"jQuery - Captain's Log", "Portland 251",
				"http://www.oscon.com/oscon2012/public/schedule/detail/23120"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"Web development without Photoshop, IDs or classes?  Improve your development time, reduced maintenance costs, SEO, accessibility and site performance with CSS. This skills-based workshop will cover including selectors, specificity, media queries, backgrounds,  gradients, animations, browser quirks, debugging and basic to advanced best practices.",
				"Estelle Weyl (Standardista.com)",
				"Presented by Estelle Weyl (Standardista.com). Web development without Photoshop, IDs or classes?  Improve your development time, reduced maintenance costs, SEO, accessibility and site performance with CSS. This skills-based workshop will cover including selectors, specificity, media queries, backgrounds,  gradients, animations, browser quirks, debugging and basic to advanced best practices.",
				"CSS: Go from Good to Great", "Portland 252",
				"http://www.oscon.com/oscon2012/public/schedule/detail/23301"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"In this session you'll learn why you can't consider UX and design an optional extra when designing mobile apps for Android, how to tell an awesome app from a terrible app, and the basics of both designing and coding for the latest and greatest Android platform (Android 4.0 and beyond). Stylish apps aren't just for that other mobile platform, and Android is surprisingly easy to get started with.",
				"Paris Buttfield-Addison (Secret Lab Pty. Ltd.), Christopher Neugebauer (Secret Lab Pty. Ltd.), Jonathon Manning (Secret Lab Pty. Ltd.)",
				"Presented by Paris Buttfield-Addison (Secret Lab Pty. Ltd.), Christopher Neugebauer (Secret Lab Pty. Ltd.), Jonathon Manning (Secret Lab Pty. Ltd.). In this session you'll learn why you can't consider UX and design an optional extra when designing mobile apps for Android, how to tell an awesome app from a terrible app, and the basics of both designing and coding for the latest and greatest Android platform (Android 4.0 and beyond). Stylish apps aren't just for that other mobile platform, and Android is surprisingly easy to get started with.",
				"Android-Fu: awesome apps for Ice Cream Sandwich and beyond",
				"Portland 255",
				"http://www.oscon.com/oscon2012/public/schedule/detail/24288"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"Want to write Node.JS applications and want someone to show you the ropes? In this workshop we will go through a quick paced introduction to node.JS, and will introduce the basic principles of writing evented IO applications. For the more advanced developers it will be interactive on the depth of certain subjects.",
				"Rik Arends (Cloud9 IDE Inc)",
				"Presented by Rik Arends (Cloud9 IDE Inc). Want to write Node.JS applications and want someone to show you the ropes? In this workshop we will go through a quick paced introduction to node.JS, and will introduce the basic principles of writing evented IO applications. For the more advanced developers it will be interactive on the depth of certain subjects.",
				"An Introduction To Evented IO Programming In Node.JS , From The Experts",
				"Portland 256",
				"http://www.oscon.com/oscon2012/public/schedule/detail/24214"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"Blender is a 3D animation suite that excels at every part of the animation pipeline, and has found its way into Hollywood blockbusters and AAA game titles. This introductory presentation will teach you 3D pipeline in a nutshell, followed by a hands-on demo where attendees can model, sculpt and render their first 3D project.",
				"Oscar Baechler (Baechler Creative, LLC)",
				"Presented by Oscar Baechler (Baechler Creative, LLC). Blender is a 3D animation suite that excels at every part of the animation pipeline, and has found its way into Hollywood blockbusters and AAA game titles. This introductory presentation will teach you 3D pipeline in a nutshell, followed by a hands-on demo where attendees can model, sculpt and render their first 3D project.",
				"Blender: a 3D introduction", "D135",
				"http://www.oscon.com/oscon2012/public/schedule/detail/23255"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"Clojure is a general-purpose language with direct support for Java, a modern Lisp dialect, and support in both the language and data structures for functional programming. Learn Clojure and you'll become a better all-around programmer. You'll also be able to write applications that have the beauty and elegance of a good scripting language and the power and reach of the JVM.",
				"Alan Dipert (Relevance, Inc.), Clinton R. Nixon (Relevance, Inc.)",
				"Presented by Alan Dipert (Relevance, Inc.), Clinton R. Nixon (Relevance, Inc.). Clojure is a general-purpose language with direct support for Java, a modern Lisp dialect, and support in both the language and data structures for functional programming. Learn Clojure and you'll become a better all-around programmer. You'll also be able to write applications that have the beauty and elegance of a good scripting language and the power and reach of the JVM.",
				"Computing with Clojure", "D136",
				"http://www.oscon.com/oscon2012/public/schedule/detail/24005"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"Have you always wanted to create hardware devices to interact with the real world? Heard about the Arduino electronics prototyping platform but not sure how to get started? When you attend this workshop you will: set up an Arduino board & software; learn how the Arduino fits into the field of physical computing; and make your Arduino respond to button presses and blink lights. Hardware is fun!",
				"Philip Lindsay (rancidbacon.com)",
				"Presented by Philip Lindsay (rancidbacon.com). Have you always wanted to create hardware devices to interact with the real world? Heard about the Arduino electronics prototyping platform but not sure how to get started? When you attend this workshop you will: set up an Arduino board & software; learn how the Arduino fits into the field of physical computing; and make your Arduino respond to button presses and blink lights. Hardware is fun!",
				"Get Started with the Arduino - A Hands-On Introductory Workshop",
				"D137-138",
				"http://www.oscon.com/oscon2012/public/schedule/detail/23941"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"This session will introduce you to the JavaFX 2 platform from the perspective of a seasoned Java developer. The breadth of JavaFX APIs will be explained through several examples that we will build out during the course of the session.",
				"Stephen Chin (Oracle)",
				"Presented by Stephen Chin (Oracle). This session will introduce you to the JavaFX 2 platform from the perspective of a seasoned Java developer. The breadth of JavaFX APIs will be explained through several examples that we will build out during the course of the session.",
				"JavaFX 2 - A Java Developer's Guide", "D139-140",
				"http://www.oscon.com/oscon2012/public/schedule/detail/24131"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"This tutorial provides a overview of the most important new features introduced in Perl 5.10 to 5.16, along with practical examples of how those features can improve the performance, robustness, and maintainability of your code",
				"Damian Conway (Thoughtstream)",
				"Presented by Damian Conway (Thoughtstream). This tutorial provides a overview of the most important new features introduced in Perl 5.10 to 5.16, along with practical examples of how those features can improve the performance, robustness, and maintainability of your code",
				"New Features of the Modern Perls (5.10 to 5.16)", "E143-144",
				"http://www.oscon.com/oscon2012/public/schedule/detail/23275"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 12:30:00-08:00",
				"You have your shiny new PostgreSQL source tarball or package, but what to do with it? In one intense tutorial, we'll go through everything need to install, configure, and maintain your new, tuned, replicated, back-uped PostgreSQL installation.",
				"Christophe Pettus (PostgreSQL Experts, Inc.)",
				"Presented by Christophe Pettus (PostgreSQL Experts, Inc.). You have your shiny new PostgreSQL source tarball or package, but what to do with it? In one intense tutorial, we'll go through everything need to install, configure, and maintain your new, tuned, replicated, back-uped PostgreSQL installation.",
				"PostgreSQL Unboxing", "E145-146",
				"http://www.oscon.com/oscon2012/public/schedule/detail/23090"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 17:00:00-08:00",
				"Join us for a day-long program exploring OpenStack, the open source cloud infrastructure platform. Originally founded at NASA and Rackspace, OpenStack has grown to be a global software community of developers collaborating on a standard and massively scalable open source cloud operating system.",
				"",
				"Join us for a day-long program exploring OpenStack, the open source cloud infrastructure platform. Originally founded at NASA and Rackspace, OpenStack has grown to be a global software community of developers collaborating on a standard and massively scalable open source cloud operating system.",
				"OpenStack Day", "F150",
				"http://www.oscon.com/oscon2012/public/schedule/detail/24987"));
		l.add(new Event(
				"2012-07-17 09:00:00-08:00",
				"2012-07-17 17:00:00-08:00",
				"So, you want to run a business; or, maybe you want to turn your Open Source project hobby into a day job.  What ever the reason you're reading the Business Leadership Day description, this one-day track has the basics to help bootstrap your business skills.",
				"Kevin Shockey (Mis Tribus)",
				"Presented by Kevin Shockey (Mis Tribus). So, you want to run a business; or, maybe you want to turn your Open Source project hobby into a day job.  What ever the reason you're reading the Business Leadership Day description, this one-day track has the basics to help bootstrap your business skills.",
				"Business Leadership Day", "F151",
				"http://www.oscon.com/oscon2012/public/schedule/detail/24988"));
		return l;
	}
}
