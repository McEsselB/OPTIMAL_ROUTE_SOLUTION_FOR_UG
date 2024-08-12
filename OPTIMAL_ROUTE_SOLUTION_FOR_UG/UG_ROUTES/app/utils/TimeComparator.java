
package app.utils;

import app.RouteDuration;

import java.util.Comparator;

public class TimeComparator implements Comparator<RouteDuration> {

    @Override
    public int compare(RouteDuration first, RouteDuration second) {
        if (first.getTime() > second.getTime())
            return 1;
        else if (first.getTime() < second.getTime())
            return -1;
        return 0;
    }
}
