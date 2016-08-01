//https://careercup.com/question?id=1408

There are atleast 3 components that are required. 
1. HTTP Request/Getting page.
2. HTML Parser
3. URL Tracker

THe first component is to request a given URL and either download it to the machine or just keep it in memory. (Downloading will need design to store the web page for easy retreival)

HTML Parser - Removes the html tags and retains text of interest (I needed only part of the page based on some pattern) and URL s in the current page. A more generic webcrawler will have to save different components like image/sound etc

URL Tracker - URL tracker makes sure that no URL is visited twice within a set time frame( A simple mechanism is a hash table with a user-defined comparator function, some urls may still point to the exact same page eg www.abc.com and www.abc.com/index.htm)

The web crawler should start with a set of URLs.

In addition to above solution - 
1. Need to handle recursive references in pages. 
2. Web crawler settings - 
2.1 Control depth, size, types, domains, retries, timeouts, threads 
2.2 Added error reporing mechanism 
2.3 Save domain passwords

Use Queue to add links in that page 
while(!queue.isEmpty()) 
{ 
ParentLink=queue.removeFirstElement(){ 
Page = Fetch(ParentLink) 
For(All Link in the Page) 
{ 

Queue.AddElement(Link) 
} 

}
