package com.magicwind.android.fitness_club.Activity;

import java.util.ArrayList;
import java.util.List;


public class ChannelDb {
	
	private static List<Channel>   selectedChannel=new ArrayList<Channel>();
	static{
		selectedChannel.add(new Channel("","训练",0,"",""));
		selectedChannel.add(new Channel("","跑步",0,"",""));
		selectedChannel.add(new Channel("","瑜伽",0,"",""));
		selectedChannel.add(new Channel("","拳击",0,"",""));
		selectedChannel.add(new Channel("","行走",0,"",""));
		selectedChannel.add(new Channel("","骑行",0,"",""));
		selectedChannel.add(new Channel("","kit",0,"",""));
	}
	public static  List<Channel> getSelectedChannel(){
		 return selectedChannel;
	}
}
