import java.util.*;

// student info
class student {
    int rollNo;
    String fullName;
    String deptName;
    double totalMarks;
    Set<String> subjectList;

    student(int rollNo, String fullName, String deptName, double totalMarks, Set<String> subjectList) {
        this.rollNo = rollNo;
        this.fullName = fullName;
        this.deptName = deptName;
        this.totalMarks = totalMarks;
        this.subjectList = subjectList;
    }

    int getRollNo() {
        return rollNo;
    }

    String getFullName() {
        return fullName;
    }

    String getDeptName() {
        return deptName;
    }

    double getTotalMarks() {
        return totalMarks;
    }

    Set<String> getSubjectList() {
        return subjectList;
    }

    public String toString() {
        return fullName + "-" + totalMarks;
    }
}

// repo store
class DataStore<T, K> {
    Map<K, T> bucket = new HashMap<>();

    void insert(K key, T val) {
        if (key != null && val != null)
            bucket.put(key, val);
    }

    T fetch(K key) {
        return bucket.get(key);
    }

    List<T> fetchAll() {
        return new ArrayList<>(bucket.values());
    }

    void remove(K key) {
        bucket.remove(key);
    }
}

public class Fourth_Session {
    public static void main(String[] args) {

        DataStore<student, Integer> recordBox = new DataStore<>();

        recordBox.insert(1, new student(1, "Rman", "CS", 82, new HashSet<>(Arrays.asList("DS", "OS"))));
        recordBox.insert(2, new student(2, "Riya", "IT", 91, new HashSet<>(Arrays.asList("CN", "DB"))));
        recordBox.insert(3, new student(3, "Karan", "CS", 44, new HashSet<>(Arrays.asList("DS", "Math"))));
        recordBox.insert(4, new student(4, "Neha", "IT", 67, new HashSet<>(Arrays.asList("CN", "OS"))));
        recordBox.insert(5, new student(5, "Raj", "CS", 95, new HashSet<>(Arrays.asList("AI", "ML"))));

        List<student> rawList = recordBox.fetchAll();

        // grouping
        Map<String, List<student>> deptBucket = new HashMap<>();
        for (student st : rawList) {
            if (st.getDeptName() == null)
                continue;
            if (!deptBucket.containsKey(st.getDeptName())) {
                deptBucket.put(st.getDeptName(), new ArrayList<>());
            }
            deptBucket.get(st.getDeptName()).add(st);
        }

        // subjects
        Set<String> subjectPool = new HashSet<>();
        for (student st : rawList) {
            if (st.getSubjectList() != null) {
                for (String sub : st.getSubjectList()) {
                    subjectPool.add(sub);
                }
            }
        }

        // top 3
        List<student> sortedList = new ArrayList<>(rawList);
        Collections.sort(sortedList, new Comparator<student>() {
            public int compare(student a, student b) {
                return Double.compare(b.getTotalMarks(), a.getTotalMarks());
            }
        });
        List<student> topperList = new ArrayList<>();
        for (int i = 0; i < sortedList.size() && i < 3; i++) {
            topperList.add(sortedList.get(i));
        }

        // Department wise average
        Map<String, Double> avgMap = new HashMap<>();
        for (String key : deptBucket.keySet()) {
            double sum = 0;
            int count = 0;
            for (student st : deptBucket.get(key)) {
                sum += st.getTotalMarks();
                count++;
            }
            if (count == 0)
                avgMap.put(key, 0.0);
            else
                avgMap.put(key, sum / count);
        }

        // Students below passing marks
        List<student> weakList = new ArrayList<>();
        for (student st : rawList) {
            if (st.getTotalMarks() < 50) {
                weakList.add(st);
            }
        }

        // Formatted report
        StringBuilder rep = new StringBuilder();
        rep.append("Report Data\n");

        for (String key : deptBucket.keySet()) {
            rep.append("Dept " + key + " size " + deptBucket.get(key).size() + "\n");
        }

        for (student st : topperList) {
            rep.append("Top " + st + "\n");
        }

        for (String key : avgMap.keySet()) {
            rep.append("Avg " + key + " " + avgMap.get(key) + "\n");
        }

        for (student st : weakList) {
            rep.append("Low " + st + "\n");
        }

        for (String sub : subjectPool) {
            rep.append("Sub " + sub + "\n");
        }

        System.out.println(rep.toString());
    }
}