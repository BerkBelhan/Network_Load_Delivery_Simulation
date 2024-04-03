import java.util.ArrayList;
import java.util.Collections;

//Mission class
public class Mission{

    private String source;
    private String middle;
    private String destination;
    private int sourcePackages;
    private int middlePackages;
    private ArrayList<Integer> dropIndex;

    //Constructor method for Mission
    public Mission(String source, String middle, String destination, int sourcePackages, int middlePackages, ArrayList<Integer> dropIndex){
        //--------------------------------------------------------
        // Summary: constructor for mission.
        // Precondition: source, middle, destination are string ; sourcepackages, middle packages are integer ; dropindex is an arraylist
        // Postcondition: variables initialized.
        //--------------------------------------------------------
        this.source = source;
        this.middle = middle;
        this.destination = destination;
        this.sourcePackages = sourcePackages;
        this.middlePackages = middlePackages;
        this.dropIndex = dropIndex;
   }



    public void completeMission(ArrayList<City> cities){
        //--------------------------------------------------------
        // Summary: This method does the mission.
        // Precondition: cities is an arraylist.
        // Postcondition: assigns the loadpackges and flowdevices to appropriate cities.
        //--------------------------------------------------------
        City sourceCity = null;
        City middleCity = null;
        City destinationCity = null;

        // find the source, middle, and destination cities.
        for (City city : cities) {
            if (city.getName().equals(source)) {
                sourceCity = city;
            }if (city.getName().equals(middle)) {
                middleCity = city;
            }if (city.getName().equals(destination)) {
                destinationCity = city;
            }
        }


        // MISSION PROGRESS

        //step 1: add loadpackages from the source city to flow device.
        //the loop will iterate until the specified times(sourcepackages * times).
        FlowDevice flowDevice = sourceCity.getFlowDevices().dequeue();

        for(int i = 0; i < sourcePackages; i++){
            LoadPackage loadPackage = sourceCity.getLoadPackages().pop();
            flowDevice.addLoadPackage(loadPackage);
        }

        //step 2: add loadpackages from the middle city to flowdevice.
        //the loop will iterate until the specified times(middlepackages * times).
        for(int i = 0; i < middlePackages; i++){
            LoadPackage loadPackage = middleCity.getLoadPackages().pop();
            flowDevice.addLoadPackage(loadPackage);
        }

        //step 3: drop the packages at according indexes.
        Collections.sort(dropIndex, Collections.reverseOrder());
        for(Integer index : dropIndex){
            LoadPackage droppedPackage =  flowDevice.getLoadPackages().removeByIndex(index);
            middleCity.getLoadPackages().push(droppedPackage);
        }

        // step 4: dropping flowdevice and the remainig packages to the destination city.

        // Move the FlowDevice to the destination city.
        destinationCity.getFlowDevices().enqueue(flowDevice);


        // Move the remaining LoadPackage objects to the destination city.
        while (!flowDevice.getLoadPackages().isEmpty()) {
            LoadPackage remainingPackage = flowDevice.removeLoadPackage();
            destinationCity.getLoadPackages().push(remainingPackage);
        }
    }
}

