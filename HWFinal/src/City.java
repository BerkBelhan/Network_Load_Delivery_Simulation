//City class
public class City {
    private String name;
    private Stack<LoadPackage> loadPackages;
    private Queue<FlowDevice> flowDevices;


    public City(String name){
        //--------------------------------------------------------
        // Summary: Constructor for city, This creates new City objects.
        // Also initializes the loadpackage stack and flowdevice queue.
        // Precondition: Parameter is a string that represents the name of the city
        // Postcondition: A new city object is created with the given name.The loadPackages stack
        //and flowDevices queue are initialized as empty data structures.
        //--------------------------------------------------------
        this.name = name;
        this.loadPackages = new Stack<>();
        this.flowDevices = new Queue<>();
    }

    //This method returns the name of the city, basically the getter for name.(getter)
    public String getName(){
        return name;
    }



    //This method adds a loadpackage object to the initialized city's stack
    public void addLoadPackage(LoadPackage loadPackage) {
        loadPackages.push(loadPackage);

    }
    //This method adds a flowdevice object to the initialized city's queue
    public void addFlowDevice(FlowDevice flowDevice){
        flowDevices.enqueue(flowDevice);

    }

    //This method returns city's loadpackage objects(getter)
    public Stack<LoadPackage> getLoadPackages(){
        return loadPackages;
    }

    //This method returns city's flowdevice objects(getter)
    public Queue<FlowDevice> getFlowDevices(){
        return flowDevices;
    }

}
