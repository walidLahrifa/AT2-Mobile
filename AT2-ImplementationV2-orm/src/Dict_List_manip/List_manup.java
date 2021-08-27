package Dict_List_manip;

import java.util.*;

import Beans.Deps;
import Beans.To_validate;
import Beans.Transactions;

public class List_manup {
	
//These methods are developed to enhance dealing with dictionaries and lists (
	public static List<Transactions> merge(List<Transactions> process_hist, List<Transactions> process_dep)
	{
	    List<Transactions> list = new ArrayList<>();
	    if(process_hist==null && process_dep!=null) {
	    	return process_dep;
	    }
	    else if(process_hist!=null && process_dep==null) {
	    	return process_hist;
	    }else if(process_hist==null && process_dep==null) {
	    	return Collections.emptyList();
	    }else {
	    
		    for(Transactions  t:process_hist) {
		    	list.add(t);}
		    for(Transactions  t:process_dep) {
		    	list.add(t);}
		    return list;
	}}
	
	public static <T> List<T> removeDuplicates(List<T> list)
    {
  
        // Create a new ArrayList
        List<T> newList = new ArrayList<T>();
  
        // Traverse through the first list
        for (T element : list) {
  
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
  
                newList.add(element);
            }
        }
  
        // return the new list
        return newList;
    }
	
	
    public static Map<String, Map<String, Object>> union_dict(Map<String, Map<String, Object>> firstdict, Map<String, Map<String, Object>> seconddict) {
        // Performing dictionary unions (firstdict ∪ seconddict)
        Map<String, Map<String, Object>> h = new HashMap<String, Map<String, Object>>();
        if (!(firstdict == null) && !(seconddict == null)) {
            final Enumeration<String> strEnum = Collections.enumeration(firstdict.keySet());
            while (strEnum.hasMoreElements()) {
                String keyInteger = strEnum.nextElement();
                Map<String, Object> values = firstdict.get(keyInteger);
                if (seconddict.get(keyInteger) == null) {
                    h.put(keyInteger, values);
                }
            }
            for (Map.Entry<String, Map<String, Object>> entry : firstdict.entrySet()) {
                h.put(entry.getKey(), entry.getValue());

            }

        } else if ((firstdict == null) && !(seconddict == null)) {
            h = seconddict;
        } else if (!(firstdict == null) && (seconddict == null)) {
            h = firstdict;
        } else {

            h = null;
        }


        return h;
    }

    public static boolean contains(List<Transactions> l1, List<Transactions> l2) {
        //checking if firstdict ⊆ seconddict
        boolean trigger = false;
        for (Beans.Transactions elem : l1) {
            if (l2.contains(elem)) {
                trigger = true;
            }else {
            	trigger= false;
            	break;
            }
        }
        return trigger;
        
    }

    public static boolean contains_forlist(List<Object> firstlist, List<Object> secondlist) {
        //checking if firstlist ⊆ socondlist
        boolean trigger = false;
        if (!(secondlist.isEmpty())){
        for (Object e : secondlist) {
            if (e == firstlist) {
                trigger= true;
            }
            else {
                trigger = false;
            }
        }
        }
        else{
            trigger=false;
        }

        return trigger;
    }
    
    
    public static boolean contains_tovalidate(Beans.To_validate tovalidate, List<Deps> deps,  List<HashMap<Beans.To_validate, List<Transactions>>> list_map) {
        //checking if firstlist ⊆ socondlist
        List<Transactions> process_dep = null;
    	for(Deps d:deps) {
    		process_dep.add(d.getTransactions());}
    	
        for(HashMap<To_validate, List<Transactions>> elem:list_map) {
            // Obtaining an iterator for the entry set
            Iterator it = elem.entrySet().iterator();
            while(it.hasNext()){
               Map.Entry me = (Map.Entry)it.next();
               if(((Beans.To_validate) me.getKey() == tovalidate) && ((List<Transactions>) me.getValue() == process_dep)) {return true;} 
            	}
        	}
        return false;
        }
}