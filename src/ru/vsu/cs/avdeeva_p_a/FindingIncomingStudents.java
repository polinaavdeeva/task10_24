package ru.vsu.cs.avdeeva_p_a;

import utils.ListUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindingIncomingStudents {

    public List<List<String>> findingIncomingStudents(List<List<String>> list, int budgetPlaces) {
        List<UniversityEntrant> universityEntrantList = toEntrantsList(list);
        sortUniverEntrants(universityEntrantList);
        Collections.reverse(universityEntrantList);
        removeEntrantWithoutCertificate(universityEntrantList);

        for (int i = 0; i <= universityEntrantList.size() - 1; i++) {
            if (i > budgetPlaces) {
                universityEntrantList.remove(i);
            }
        }

        List<List<String>> incomingEntrants = new ArrayList<>();

        for (UniversityEntrant universityEntrant : universityEntrantList) {
            List<String> entrant = universityEntrantToList(universityEntrant);
            incomingEntrants.add(entrant);
        }

        return incomingEntrants;
    }

    private List<String> universityEntrantToList(UniversityEntrant universityEntrant) {
        String line = universityEntrant.toString();
        return ListUtils.toList(line);
    }

    private List<UniversityEntrant> toEntrantsList(List<List<String>> list) {
        List<UniversityEntrant> newList = new ArrayList<>(list.size());

        for (int i = 0; i < list.size(); i++) {
            newList.add(i, new UniversityEntrant(list.get(i)));
        }

        return newList;
    }

    private void sortUniverEntrants(List<UniversityEntrant> listOfEntrants) {
        listOfEntrants.sort(new Comparator<UniversityEntrant>() {
            public int compare(UniversityEntrant left, UniversityEntrant right) {
                if (left.getArithmeticMeanGrade() == right.getArithmeticMeanGrade()) return 0;
                else if (left.getArithmeticMeanGrade() > right.getArithmeticMeanGrade()) return 1;
                else return -1;
            }
        });
    }

    private void removeEntrantWithoutCertificate(List<UniversityEntrant> universityEntrants) {
        String withoutCertificate = "нет";

        for (int i = 0; i <= universityEntrants.size() - 1; i++) {
            if (withoutCertificate.equals(universityEntrants.get(4))) {
                universityEntrants.remove(i);
            }
        }
    }
}

