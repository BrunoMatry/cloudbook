package com.google.code.gossip.examples;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.google.code.gossip.GossipMember;
import com.google.code.gossip.GossipService;
import com.google.code.gossip.GossipSettings;
import com.google.code.gossip.LogLevel;
import com.google.code.gossip.RemoteGossipMember;

public class GwendalExample {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		ArrayList<GossipMember> startupMembers = new ArrayList<GossipMember>();
		ArrayList<GossipService> services = new ArrayList<GossipService>();
		for(int i = 50000 ; i <= 50099 ; i++) {
			startupMembers.add(new RemoteGossipMember(ip, i));
			services.add(new GossipService(ip, i, LogLevel.DEBUG, startupMembers, new GossipSettings()));
		}
		
		for(GossipService s : services)
			s.start();
	}

}
