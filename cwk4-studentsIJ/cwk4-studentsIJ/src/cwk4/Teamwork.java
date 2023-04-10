package cwk4; 


/**
 * Details of your team
 *
 * @author Andrei Cirlig, Janine Obiri, Lewis Turnbull, Zohaib Rehman
 * @version 06/04/2023
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "4";

        details[1] = "Cirlig";
        details[2] = "Andrei";
        details[3] = "20049583";

        details[4] = "Obiri";
        details[5] = "Janine";
        details[6] = "20037420";

        details[7] = "Turnbull";
        details[8] = "Lewis";
        details[9] = "20052194";


        details[10] = "Rehman";
        details[11] = "Zohaib";
        details[12] = "20070280";

    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
