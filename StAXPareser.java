package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAXPareser {
   public static void main(String[] args) {
      boolean bTitle = false;
      boolean bType = false;
      boolean bAmount = false;
      ArrayList<Computer> catalogComputers = new ArrayList<>();
      int id1 = 0;
      String title1="";
  	  String type1 = "";
  	  int amount1 = 0;
      
      try {
         XMLInputFactory factory = XMLInputFactory.newInstance();
         XMLEventReader eventReader =
         factory.createXMLEventReader(
            new FileReader("src/test/computerStore.xml"));

            while(eventReader.hasNext()){
               XMLEvent event = eventReader.nextEvent();
               switch(event.getEventType()){
                  case XMLStreamConstants.START_ELEMENT:
                     StartElement startElement = event.asStartElement();
                     String qName = startElement.getName().getLocalPart();
                     if (qName.equalsIgnoreCase("computer")) {
                        System.out.print("Start Element : computer,");
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        String id = attributes.next().getValue();
                        id1 = Integer.parseInt(id);
                        System.out.println(" id : " + id);
                     } else if (qName.equalsIgnoreCase("title")) {
                        bTitle = true;
                     } else if (qName.equalsIgnoreCase("type")) {
                        bType = true;
                     } else if (qName.equalsIgnoreCase("amount")) {
                        bAmount = true;
                     }				        
                     break;
                  case XMLStreamConstants.CHARACTERS:
                     Characters characters = event.asCharacters();
                     if(bTitle){
                        System.out.println("First Name: " 
                        + characters.getData());
                        title1 = characters.getData();
                        bTitle = false;
                     }
                     if(bType){
                        System.out.println("Last Name: " 
                        + characters.getData());
                        type1 = characters.getData();
                        bType = false;
                     }
                     if(bAmount){
                        System.out.println("Nick Name: " 
                        + characters.getData());
                        amount1 = Integer.parseInt(characters.getData());
                        bAmount = false;
                     }
                     break;
                  case  XMLStreamConstants.END_ELEMENT:
                     EndElement endElement = event.asEndElement();
                     if(endElement.getName().getLocalPart().equalsIgnoreCase("computer")){
                    	catalogComputers.add(new Computer(id1, title1, type1, amount1));
                    	System.out.println("End Element : computer");
                        System.out.println();
                     }
                     break;
               }		    
            }
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (XMLStreamException e) {
            e.printStackTrace();
      }
      
  	for (Computer comp: catalogComputers) 
		System.out.println("Computer: ID=" + comp.getId() + " Title=" + comp.getTitle() + " Type=" + comp.getType() + " Amount=" + comp.getAmount());
   }
}