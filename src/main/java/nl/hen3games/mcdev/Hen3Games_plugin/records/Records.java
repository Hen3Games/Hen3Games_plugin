package nl.hen3games.mcdev.Hen3Games_plugin.records;

import java.util.ArrayList;

public class Records {

    public static class playerRec {
        public String Uuid;
        public String Name;
        public String colour;
        public rankRec Rank;
    }

    public static class rankRec{
        public String name;
        public String colour;
        public ArrayList<rankMemberRec> member = new ArrayList<rankMemberRec>();
    }

    public static class rankMemberRec{
        public playerRec player;
    }

    public static ArrayList<playerRec> playerList = new ArrayList<playerRec>();
    public static ArrayList<rankRec> rankList = new ArrayList<rankRec>();
}
