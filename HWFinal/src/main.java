import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;


//main class
public class main {


    public static ArrayList<String> readData(String fileName){
        //--------------------------------------------------------
        // Summary: readData method reads the text files.
        // Precondition: filename is a string.
        // Postcondition: returned the lines.
        //--------------------------------------------------------
        ArrayList<String> lines = new ArrayList<>();
        try{
            File f = new File(fileName);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                lines.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();

        }
        return lines;
    }


    public static ArrayList<Mission> readMissionData(){
        //--------------------------------------------------------
        // Summary: readMissionData method reads the mission.txt file separately.
        //There are different signs in the mission file, so we need to check and parse the lines accordingly.
        //So, that's why we are reading the mission.txt separately. but the method has the same logic with the above reader.
        //--------------------------------------------------------
        ArrayList<Mission> missions = new ArrayList<>();
        try{
            File f = new File("sampleIO/set2/Missions.txt");
            Scanner msc = new Scanner(f);
            while(msc.hasNextLine()){
                String data = msc.nextLine();
                Mission mission = parseMission(data);
                missions.add(mission);
            }
        }catch (FileNotFoundException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        return missions;
    }



    public static Mission parseMission(String missionData){
        //--------------------------------------------------------
        // Summary: Parse method for the data we read from MISSION, returns a new mission object.
        // Precondition: missiondata a string
        // Postcondition: A flowdevice object is created. The loadPackages DoublyLinkedList is initialized as an empty list.
        //--------------------------------------------------------

        //the content in mission.txt is like xxxx-xxxx-xxxx-xxxx-xxxx-xxxx,xxxx, so we parse with "-" and ","
        String[] parts = missionData.trim().split("[-,]");
        //first of all we are determining the 3 cities
        String source = parts[0];
        String middle = parts[1];
        String destination = parts[2];

        //Then we get the number of packages from each base station
        int sourcePackages = Integer.parseInt(parts[3]);
        int middlePackages = Integer.parseInt(parts[4]);

        //Later we get the drop indexes (referring the c1,c2 part in mission)
        ArrayList<Integer> dropIndexList = new ArrayList<>();
        for (int i = 5; i < parts.length; i++){
            dropIndexList.add(Integer.parseInt(parts[i]));
        }
        //returns a new mission object
        return new Mission(source, middle, destination, sourcePackages, middlePackages, dropIndexList);
    }

    public void writeToFile(ArrayList<City> cities) {
        //--------------------------------------------------------
        // Summary: writer method to write the output into the desired file.
        // Precondition: cities list is an arraylist.
        // Postcondition: The desired file is written.
        //--------------------------------------------------------
        try {
            PrintWriter writer = new PrintWriter("sampleIO/set2/test.txt", "UTF-8");

            for (City city : cities) {
                writer.println(city.getName());
                writer.println("Loads:");


                Stack<LoadPackage> loads = city.getLoadPackages();
                while (!loads.isEmpty()) {
                    LoadPackage load = loads.pop();
                    writer.println(load);
                }

                writer.println("Flow Devices:");


                Queue<FlowDevice> flowDevices = city.getFlowDevices();
                while (!flowDevices.isEmpty()) {
                    FlowDevice device = flowDevices.dequeue();
                    writer.println(device);
                }

                writer.println("-------------");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        //calling the readData and readMissionData functions to read the text files.
        ArrayList<String> destinations = readData("sampleIO/set2/destinations.txt");
        ArrayList<String> loads = readData("sampleIO/set2/loads.txt");
        ArrayList<String> flowdevices = readData("sampleIO/set2/flowdevices.txt");
        ArrayList<Mission> missions = readMissionData();




        // Summary: 3 for loops to store city, loadpackage, flowdevice data.
        // This where we started storing the data we read.
        //--------------------------------------------------------

        ArrayList<City> cities = new ArrayList<>();
        //takes the names of the data from destinations and assigns new city objects.
        for(String Cname : destinations ){
            City city = new City(Cname);
            cities.add(city);
        }

        //assign load packages to cities, basically storing them accordingly.
        for(String load : loads){
            // we need to separate each line to check the corresponding city names.
            String[] parts = load.split(" ");
            // we are taking [1] because format is like this p1 Istanbul so we istanbul is in the index of 1.
            String cityName = parts[1];
            // this will get 1 from p1 for example so getting the index from the name.
            int index = Integer.parseInt(parts[0].substring(1));
            //iterate thorough the cities to find a match.
            for(City city : cities){
                if(city.getName().equals(cityName)){
                    city.addLoadPackage(new LoadPackage(index));
                }
            }
        }

        //assign flow devices to cities
        for(String flowD : flowdevices){
            String [] parts  = flowD.split(" ");
            String cityName = parts[1];
            //iterate thought the cities to find a match.
            for(City city : cities){
                if(city.getName().equals(cityName)){
                    city.addFlowDevice(new FlowDevice());
                }
            }
        }

        //complete missions by passing the city list. (we actually have 1 mission but in a scenario we need this).
        for(Mission mission : missions){
            mission.completeMission(cities);
        }

        //creating an instance of the main class and write the city information to the result.txt file.
        main instance = new main();
        instance.writeToFile(cities);

    }

}
