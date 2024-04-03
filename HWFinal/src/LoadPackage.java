//LoadPackage class
public class LoadPackage {
    private  int index;

    public LoadPackage(int index){
        //--------------------------------------------------------
        // Summary: Constructor for flowdevice, This creates new flowdevice objects.
        // Also initializes the loadpackages doublylinkedlist and assigns id to them.
        // Precondition: index is an integer
        // Postcondition: A flowdevice object is created. The loadPackages DoublyLinkedList is initialized as an empty list.
        //--------------------------------------------------------
        this.index = index;
    }

    //This method returns the index of the load package.
    public int getIndex(){
        return index;
    }

    //This method returns string representation of the flow device.
    //This was added again, to get a meaningfull display rather than hashcoded memory address of it.
    @Override
    public String toString() {
        return "P" + index;
    }

}
