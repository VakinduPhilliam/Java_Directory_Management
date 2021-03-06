//Here�s an example to show you how to search a file named �post.php� from directory �/Users/mkyong/websites� and all its subdirectories recursively.
// FileSearch.java


package com.mkyong;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

  private String fileNameToSearch;
  private List<String> result = new ArrayList<String>();
	
  public String getFileNameToSearch() {
	return fileNameToSearch;
  }

  public void setFileNameToSearch(String fileNameToSearch) {
	this.fileNameToSearch = fileNameToSearch;
  }

  public List<String> getResult() {
	return result;
  }

  public static void main(String[] args) {

	FileSearch fileSearch = new FileSearch();
  
        //try different directory and filename :)
	fileSearch.searchDirectory(new File("/Users/mkyong/websites"), "post.php");

	int count = fileSearch.getResult().size();
	if(count ==0){
	    System.out.println("\nNo result found!");
	}else{
	    System.out.println("\nFound " + count + " result!\n");
	    for (String matched : fileSearch.getResult()){
		System.out.println("Found : " + matched);
	    }
	}
  }

  public void searchDirectory(File directory, String fileNameToSearch) {

	setFileNameToSearch(fileNameToSearch);

	if (directory.isDirectory()) {
	    search(directory);
	} else {
	    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
	}

  }

  private void search(File file) {

	if (file.isDirectory()) {
	  System.out.println("Searching directory ... " + file.getAbsoluteFile());
		
            //do you have permission to read this directory?	
	    if (file.canRead()) {
		for (File temp : file.listFiles()) {
		    if (temp.isDirectory()) {
			search(temp);
		    } else {
			if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {			
			    result.add(temp.getAbsoluteFile().toString());
		    }

		}
	    }

	 } else {
		System.out.println(file.getAbsoluteFile() + "Permission Denied");
	 }
      }

  }

}


//Output

//Searching directory ... /Users/mkyong/websites
//Searching directory ... /Users/mkyong/websites/wp-admin
//Searching directory ... /Users/mkyong/websites/wp-admin/css
//Searching directory ... /Users/mkyong/websites/wp-admin/images
//Searching directory ... /Users/mkyong/websites/wp-admin/images/screenshots
//Searching directory ... /Users/mkyong/websites/wp-admin/includes
//Searching directory ... /Users/mkyong/websites/wp-admin/js
//Searching directory ... /Users/mkyong/websites/wp-admin/maint
//Searching directory ... /Users/mkyong/websites/wp-admin/network
//Searching directory ... /Users/mkyong/websites/wp-admin/user
//Searching directory ... /Users/mkyong/websites/wp-content
//Searching directory ... /Users/mkyong/websites/wp-content/plugins
//long list, omitted.


//Found 3 result!

//Found : /Users/mkyong/websites/wp-admin/includes/post.php
//Found : /Users/mkyong/websites/wp-admin/post.php
//Found : /Users/mkyong/websites/wp-includes/post.php