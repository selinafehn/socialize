package com.mosbach.utils;
import com.mosbach.demo.data.api.Voting;
import com.mosbach.demo.data.api.VotingManager;
import com.mosbach.demo.data.impl.PostgresDBVotingManagerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class OptionSorter {

    static VotingManager votingManager = PostgresDBVotingManagerImpl.getPostgresDBVotingManagerImpl();

    public static Map<String, Integer> Sorter (String meetupid) {

        List<Voting> votings = votingManager.readVotingbyMeetupID(meetupid);

        Map <String,Integer> map = new HashMap<> ();

        //init (?)
        map.put("opt1",0);
        map.put("opt2",0);
        map.put("opt3",0);
        map.put("opt4",0);
        map.put("opt5",0);
        map.put("opt6",0);
        map.put("opt7",0);

        for (Voting v : votings){
            if (v.getOpt1())map.put("opt1", map.get("opt1")+1);
            if (v.getOpt2())map.put("opt2", map.get("opt2")+1);
            if (v.getOpt3())map.put("opt3", map.get("opt3")+1);
            if (v.getOpt4())map.put("opt4", map.get("opt4")+1);
            if (v.getOpt5())map.put("opt5", map.get("opt5")+1);
            if (v.getOpt6())map.put("opt6", map.get("opt6")+1);
            if (v.getOpt7())map.put("opt7", map.get("opt7")+1);
        }

        //sort
        //Stream<Map.Entry<String, Integer>> sorted = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors);

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<String, Integer> sortedmap = new HashMap<>();

        for (Map.Entry<String,Integer> entry : list){
            sortedmap.put(entry.getKey(), entry.getValue());
        }

        return sortedmap;

    }

}
