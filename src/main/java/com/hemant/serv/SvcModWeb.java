package com.hemant.serv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.tags.Param;

@RestController
public class SvcModWeb {
	private String dir = "E:\\Angular\\data";
		
		@RequestMapping(path="getTextList", method = RequestMethod.GET)
		public String[] getTextList(String route)
		{
			String url = dir + "\\text\\" + route;
			File fin = new File(url);
			File[] fArr = fin.listFiles();
			String[] result = new String[fArr.length];
			for(int i = 0; i<result.length; i++)
				result[i] = fArr[i].getName();
			return result;
		}
		
		@RequestMapping(path="getImgList", method=RequestMethod.GET)
		public String[] getImgList(String route)
		{
			String url = dir + "\\image\\" + route;
			File fin = new File(url);
			File[] fArr = fin.listFiles();
			String[] result = new String[fArr.length];
			for(int i = 0; i<result.length; i++)
				result[i] = fArr[i].getName();
			return result; 
		}
		
		@RequestMapping(path="getText", method = RequestMethod.GET)
		public String getText(@RequestParam String route, @RequestParam String filename)
		{
			String data = "";
			String url = dir + "\\text\\" + route + "\\" + filename;
			File fin = new File(url);
			try
			{
				if(fin.exists() && fin.canRead())
				{
					FileReader fread = new FileReader(fin);
					BufferedReader buffread = new BufferedReader(fread);
					String temp = "";
					while((temp = buffread.readLine())!=null)
						data = data.concat(temp);
					buffread.close();
				}
				else
					return "File not found";
			}
			catch(Exception ex)
			{
				System.out.println("Exception: "+ex.toString());
				return "Exception in reading file";
			}
			return data;
		}
		
		
		
		

	}

