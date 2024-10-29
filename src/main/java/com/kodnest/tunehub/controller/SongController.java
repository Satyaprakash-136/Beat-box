package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.SongService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SongController 
{
@Autowired
SongService songService;

@PostMapping("/addsongs")

public String addSong(@ModelAttribute Song song)
{
	String name=song.getName();
	
	boolean songExists=songService.songExists(name);
	
	if(songExists==false)
	{
		songService.saveSong(song);
		System.out.println("Song added Successfully");
	}
	else 
	{
		System.out.println("Duplicate song");
	}
	return "adminhome";
}

@GetMapping("/playsongs")

public String playsongs(Model model) 
	
	{
	boolean Premium=true;
	if(Premium)	
		{	
	List<Song> songList=songService.fetchAllSongs();
	model.addAttribute("songs",songList);
	return "viewSongs";
		}
	else
			{
		return "pay";
			}
	}

@GetMapping("/viewsongs")
public String viewSongs(Model model) 
{
	List<Song> songList=songService.fetchAllSongs();
	model.addAttribute("songs",songList);
	return "viewSongs";
	}
}








