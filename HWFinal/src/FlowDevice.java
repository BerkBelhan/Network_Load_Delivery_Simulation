//FlowDevice class
public class FlowDevice {

    private DoublyLinkedList<LoadPackage> loadPackages;
    private static int count = 1;
    private int id;


    //constructor for flow device
    public FlowDevice(){
        //--------------------------------------------------------
        // Summary: Constructor for flowdevice, This creates new flowdevice objects.
        // Also initializes the loadpackages doublylinkedlist and assigns id to them.
        // Precondition: doesn't take a parameter
        // Postcondition: A flowdevice object is created. The loadPackages DoublyLinkedList is initialized as an empty list.
        //--------------------------------------------------------
        this.loadPackages = new DoublyLinkedList<>();
        this.id = count++;
    }

    //This method adds a loadpackage object to the flow device’s loadpackages list.
    public void addLoadPackage(LoadPackage loadPackage){
        this.loadPackages.append(loadPackage);
    }

    //This method removes the first loadpackage object from the flow device's loadpackages list.
    public LoadPackage removeLoadPackage() {
        return this.loadPackages.removeFirst().value;
    }

    //This method returns the flowdevices list of loadpackage objects.
    public DoublyLinkedList<LoadPackage> getLoadPackages() {
        return this.loadPackages;
    }

    //This method returns string representation of the flow device.
    //this was added because i was getting the memory addresses when i printed out the flowdevices.
    @Override
    public String toString() {
        return "T" + id;
    }

}
