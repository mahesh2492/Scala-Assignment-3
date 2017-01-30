import java.io._
import scala.io.Source
import java.io.{File,FileNotFoundException, IOException}
trait FileManipulation {
  def capitalizeAll(file: File): Boolean
  def countWords(filename: File): Boolean

}

class AllModules extends FileManipulation
{
/* Read files from a directory
For each file read contents of the file
Capitalize the contents of the file 
Then write the capitalized content into another output file with the same name in a different directory */
    def capitalizeAll(file : File): Boolean =
    {
	    val pw = new PrintWriter(new File(s"/home/akhil/Desktop/mahesh/${file.getName}" ))
	    val text = new StringBuilder("")
	    
		for(line <- Source.fromFile(file).getLines)
        {
			text.append(s"${line.toUpperCase}\n")
			
		}
		
		pw.println(text.toString())
		
		if(pw.checkError()) {
		
		    println("error while writing data")
		    
		}
		
		pw.close()
			

 true   }

    def readFromFile(filename: File): Boolean =
   {
       val bufferedSource = Source.fromFile(filename)
		
	try {
		for (line <- bufferedSource.getLines) {
			println(line)
		}
		bufferedSource.close
		
                true
	    } 
	catch {
		case e: FileNotFoundException => { println("Couldn't find that file."); false }
		case e: IOException => { println("Got an IOException!"); false }
                
	      }
     
    }
   
   /* Read all the unique words from the file
For each word calculate the word count 
For eg: for a file containing the content
“Hello world, hello”
In this case the summary would be 
hello -> 2
world -> 1
For simplicity cases should be ignored – Hello and hello mean the same word
This summary should then be added to an output file with the same name in a different directory 
*/
  def countWords(file : File): Boolean =
    {
	    val pw = new PrintWriter(new File(s"/home/akhil/Desktop/shivangi/${file.getName}" ))
	    val text = new StringBuilder("")
	    
		
           val mymap = Source.fromFile(file).getLines.flatMap(_.split("\\W+")).toList.groupBy((word: String) => word).mapValues(_.length)
            for ((word,count) <- mymap) text.append(word+" = "+count+"\n")
			//text.append(s"${line.toUpperCase}\n")
			
	
		 pw.println(text.toString())
		
		if(pw.checkError()) {
		
		    println("error while writing data")
		    
		}
		
		pw.close()
			
true   }
   def extractor(filepath: String): List[String] =
  {
     val d = new File(filepath)
    if(d.exists  && d.isDirectory) 
     (d.listFiles.filter(_.getName.endsWith(".txt"))).toList.map(_.getName)
     else
     List[String]() 
     
   }
 
}
object ExtractTransformLoading extends App {

    val myobj= new AllModules

    val filepath = "/home/akhil/Desktop/Files/"
	val folder : File = new File(filepath)
	
	val listOfFiles : Array[File] = folder.listFiles()
	
	//println(listOfFiles.length)

	listOfFiles.foreach(file => {

		if(file.isFile) {
		     myobj.capitalizeAll(file)
		      myobj.countWords(file)
		}
		else
		    println("file")

	})
	
	
	
} 





