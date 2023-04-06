package cwk4;

import java.io.*;



    public enum PlayerRole implements Serializable
    {
        ADMIRAL("Admiral"), WAR ("War Minister") , INT ("Head of intelligience");
        private String role;

        private PlayerRole(String ty) {role = ty;}

        public String toString()
        {
            return role;
        }
    }

