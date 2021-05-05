public class Verification {
    
    public String checkUserMovement(String input){
        String output = "";

        for (char movement : input.toCharArray()) {
            if(movement == 'L' || movement == 'R' || movement == 'U' || movement == 'D'){
                output += movement;
            }
        }
        return output;
    }
}
