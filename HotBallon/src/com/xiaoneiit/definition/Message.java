package com.xiaoneiit.definition;

public class Message {
	public static final String[] DATA = {
		"Awesome!",
		"Need play with this now",
		"I checked out your branch, but I don't see any particles, just shaking",
		"@timaschew Which browser + version are you on?",
		"I want this for sublime!",
		"Same as @timaschew. I'm on Chrome 46.0.2490.86 (64-bit) (OSX) and I see only shaking.",
		"And also I want a class' style definition to be animated into the class with some kind of particle transformation...",
		"I tried on chrome, firefox and safari, same result on all browsers.",
		"I'm using OS X El Capitan",
		"I could watch this forever (sorry for getting pinged for this pointing comment)",
		"@enrique-ramirez @timaschew Alright I'll look into it. Since you're getting the shaking I assume you hit \"Power Mode\" by getting your combo to 200, which is when the particles should also come into effect.",
		"Yes, shaking starts at 200 points, in power mode. ",
		"I tried to continue until 1000, but it was still just shaking.",
		"Does the screen shake go up to \"Vlambeer\"?",
		"Dude, that's pretty amazing!",
		"Love it!!",
		"Same issue as previous posters - no particle effects are shown.",
		"Running Chrome 46.0.2490.86m on Windows 10.1",
		"Bugfix for particle effects positioning being off due to scrolling",
		"Hmm I can't seem to reproduce this in any browser. ",
		"Ah, it does work with that version. Where could our builds go wrong?",
		"if you've all been scrolled too far down to see the particles.",
		"You could try it locally again with the latest commit.",
		"Yeah it works, awesome, thanks!",
		"Code with feelings",
		"Literally the best thing i've seen",
		"Awesome man",
		"Zomg!!!",
		"Cool !",
		"awesome",
		"hahaahaha, awesome!",
		"I have lost my eyes .. D _ D",
		"package for sublime please ~",
		"looks awesome",
		"I dont know if it's practical, but it's really cool.",
		"wow when my friend ask me what i'm doing i can show them this",
		"Now we can code and feel like Rambo with a machine gun",
		"Awsome Bro <3",
		"Wow! Awesome! Want it badly for PHPStorm!",
		"This is what kind of editor?",
		"Well, for those of you who want this on sublime,",
		"turns out its API doesn't give us much flexibility on the viewport (at least on Sublime Text 2 API). ",
		" I had a look at it for a couple of hours but couldn't find a workaround :(",
		"I just tried it! That's so f**king amazing! Great work!",
		"I thought maybe near in future I can have it in sublime text ?"
	};
	
	public static int getSize(){
		return DATA.length;
	}
	
	public static String getMessage(int index){
		return DATA[index];
	}
}
