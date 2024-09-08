public class MyString {

    public static String formatString(String input, int len){
        //A long string of padding to avoid use of a loop
        String formattedInput = input.trim(); 
        String spaces = "                            "; 
        int strLen = formattedInput.length(); 
        //input string is too long
        if(strLen < len){
            return formattedInput + spaces.substring(0, len  - strLen);
        } else if (strLen > len){ //input string is too short
            return formattedInput.substring(0, len); 
        } else {
            //input string is just the right size
            return formattedInput; 
        }
    }

    public static String convertBoolean(boolean in){
        if(in){
            return "True"; 
        } else {
            return "False";
        }
    }

    public static String repeat(String in, int num){
        String result = ""; 
        for(int count = 1; count <= num; count++){
            result += in; 
        }

        return result; 
    }




}