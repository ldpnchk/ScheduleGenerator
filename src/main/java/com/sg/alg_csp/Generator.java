package com.sg.alg_csp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sg.entity.*;

public class Generator {
	
	private List<Restriction> restrictions = new ArrayList<Restriction>();
	private ArrayList<HashMap<Lesson, Condition>> result = new ArrayList<HashMap<Lesson, Condition>>();
	
	public Generator(List<Lesson> lessons, List<Classroom> classrooms, List <Daytime> daytimes, List<Periodtime> periodtimes, List<Restriction> restrictions){
		this.restrictions = restrictions;
		
		List<Condition> conditions = new ArrayList<Condition>();
		for (Daytime daytime : daytimes)
			for (Periodtime periodtime : periodtimes)
				for (Classroom classroom : classrooms)
					conditions.add(new Condition(daytime, periodtime, classroom));
		
		process(lessons, conditions, new HashMap<Lesson, Condition>());
		
	}
	
	public ArrayList<HashMap<Lesson, Condition>> getResult() {
		return result;
	}


	private void process(List<Lesson> lessons, List<Condition> conditions, HashMap<Lesson, Condition> currentResult){
		System.out.println("1");
		if (lessons.size() == 0){
			System.out.println("2");
			result.add(currentResult);
		}
		
		else{
			System.out.println("3");
			lessons = sortByMRV(lessons, conditions, currentResult);
			for (int i = 0; i < lessons.size(); i++){
				System.out.println("4");
				if (result.size() > 0){
					return;
				}
				System.out.println("5");
				ArrayList<Condition> curConditions = sortByLCV(lessons.get(i), lessons, conditions, currentResult);
				System.out.println("6");
				System.out.println(curConditions.size());
				for (int j = 0; j < curConditions.size(); j++){
					System.out.println("7");
					if (result.size() > 0){
						return;
					}
					System.out.println("8");
					HashMap<Lesson, Condition> newCurrentResult = new HashMap<Lesson, Condition>(currentResult);
					System.out.println("9");
					newCurrentResult.put(lessons.get(i), curConditions.get(j));
					System.out.println("10");
					
					ArrayList<Lesson> newLessons = new ArrayList<Lesson>(lessons);
					System.out.println("11");
					newLessons.remove(lessons.get(i));
					System.out.println("12");
					ArrayList<Condition> newConditions = new ArrayList<Condition>(conditions);
					System.out.println("13");
					newConditions.remove(curConditions.get(j));
					System.out.println("14");
					process(newLessons, newConditions, newCurrentResult);
				}
				
			}
			
		}
		
	}
	
	private List<Lesson> sortByMRV(List<Lesson> lessons, List<Condition> conditions, HashMap<Lesson, Condition> currentResult){
		HashMap<Lesson, Integer> resultMRV = new LinkedHashMap<Lesson, Integer>();
		for (Lesson lesson: lessons){
			resultMRV.put(lesson, 0);
			for (Condition condition : conditions){
				if (isSatisfied(lesson, condition, currentResult, restrictions)){
					int t = resultMRV.get(lesson).intValue();
					resultMRV.put(lesson, t + 1);
				}
			}
		}
		
		resultMRV = (HashMap<Lesson, Integer>) sortByValue(resultMRV);
		
		ArrayList<Lesson> l = new ArrayList<Lesson>();
		l.addAll(resultMRV.keySet());
		
		return l;
		
	}
	
	private ArrayList<Condition> sortByLCV(Lesson l, List<Lesson> lessons, List<Condition> conditions, HashMap<Lesson, Condition> currentResult){
		System.out.println("01");
		HashMap<Condition, Integer> resultLCV = new HashMap<Condition, Integer>();
		System.out.println(conditions.size());
		System.out.println(lessons.size());
		for (Condition condition : conditions){
			if (isSatisfied(l, condition, currentResult, restrictions)){
				System.out.println("02");
				resultLCV.put(condition, 0);
			}
		}
		
		for (Lesson lesson: lessons){
			for (Condition condition : resultLCV.keySet()){
				if (isSatisfied(lesson, condition, currentResult, restrictions)){
					System.out.println("03");
					int t = resultLCV.get(condition).intValue();
					resultLCV.put(condition, t + 1);
				}
			}
		}
		
		resultLCV = (HashMap<Condition, Integer>) sortByValue(resultLCV);
		
		ArrayList<Condition> c = new ArrayList<Condition>();
		c.addAll(resultLCV.keySet());
		
		return c;
		
	}
	
	private static boolean isSatisfied(Lesson lesson, Condition condition, HashMap<Lesson, Condition> currentResult, List<Restriction> restrictions) {
		return isSatisfiedByCapacity(lesson, condition) && 
				isSatisfiedByTools(lesson, condition) && 
				isSatisfiedByRoomType(lesson, condition) && 
				isSatisfiedByCoincidences(lesson, condition, currentResult) &&
				isSatisfiedByRestrictions(lesson, condition, restrictions);
	}

	private static boolean isSatisfiedByCapacity(Lesson lesson, Condition condition) {
		if (lesson.getStudents().size() > condition.getClassroom().getCapacity()){
			return false;
		}
		return true;
	}
	
	private static boolean isSatisfiedByTools(Lesson lesson, Condition condition) {
		Set<Tool> tools = lesson.getTools().keySet();
		for(Tool tool : tools) {
			if ((lesson.getTools().get(tool) && !condition.getClassroom().getTools().contains(tool)) 
					|| (!lesson.getTools().get(tool)) && condition.getClassroom().getTools().contains(tool)){
				return false;
			}
		}
		return true;
	}
	
	private static boolean isSatisfiedByRoomType(Lesson lesson, Condition condition) {
		return lesson.getRoomType().equals(condition.getClassroom().getRoomType());
	}
	
	private static boolean isSatisfiedByCoincidences(Lesson lesson, Condition condition, HashMap<Lesson, Condition> currentResult) {
		Set<Lesson> lessons = currentResult.keySet();
		for (Lesson l : lessons){
			if (currentResult.get(l).getDay().equals(condition.getDay()) && currentResult.get(l).getPeriod().equals(condition.getPeriod()) && (hasCoincidences(lesson, l))){
				return false;
			}
		}
		return true;
	}

	private static boolean hasCoincidences(Lesson lesson1, Lesson lesson2) {
		if (lesson1.getLecturer().equals(lesson2.getLecturer())){
			return true;
		}
		for (int i = 0; i < lesson1.getStudents().size(); i++){
			if (lesson2.getStudents().contains(lesson1.getStudents().get(i))){
				return true;
			}
		}
		return false;
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map){
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort( list, new Comparator<Map.Entry<K, V>>(){
			
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2){
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list)
		{
			result.put(entry.getKey(), entry.getValue());
		}
		
		return result;
	}
	
	private static boolean isSatisfiedByRestrictions(Lesson lesson, Condition condition, List<Restriction> restrictions) {
		for (Restriction r : restrictions){
			if (lesson.getDiscipline().equals(r.getDiscipline()) || 
				lesson.getLecturer().equals(r.getLecturer())){
				if (r.isSelection()){
					if ((r.getClassroom() != null && !condition.getClassroom().equals(r.getClassroom())) ||
						(r.getDaytime() != null && !condition.getDay().equals(r.getDaytime())) ||
						(r.getPeriodtime() != null && !condition.getPeriod().equals( r.getPeriodtime()))){
						return false;
					}
				}
				else{
					if ((r.getDiscipline() == null || lesson.getDiscipline().equals(r.getDiscipline())) &&
						(r.getLecturer() == null || lesson.getLecturer().equals(r.getLecturer())) &&
						(r.getClassroom() == null || condition.getClassroom().equals(r.getClassroom())) &&
						(r.getDaytime() == null || condition.getDay().equals(r.getDaytime())) &&
						(r.getPeriodtime() == null || condition.getPeriod().equals( r.getPeriodtime()))){
						return false;
					}
				}
			}
		}
		return true;
	}
}
