import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class EventPackerTest {

    @Test
    public void testMeetingNoConflict() {
        int[][] meetings = new int[][]{new int[]{0, 1, 2,}, new int[]{1, 2}};
        EventPacker evtPcker = new EventPacker();
        Agenda result = evtPcker.getMaxAttendees(meetings);
        System.out.println("Meetings: " + result.toString());
        assertTrue(validateNoConflicts(result));

        assertEquals(3, result.attendeeCount);
    }

    @Test
    public void testMeetingsSimpleConflict() {
        int[][] meetings = new int[][]{ new int[] {0,1}, new int[] {0}, new int[] {1}};
        EventPacker evtPcker = new EventPacker();
        Agenda result = evtPcker.getMaxAttendees(meetings);
        System.out.println("Meetings: " + result.toString());
        assertTrue(validateNoConflicts(result));

        assertEquals(2, result.attendeeCount);
    }

    @Test
    public void testMeetingsMultiConflict() {
        int[][] meetings = new int[][]{
                new int[]{4, 10},
                new int[]{3, 4, 12},
                new int[]{0, 8, 9, 10, 13},
                new int[]{1, 5, 7},
                new int[]{2, 6},
                new int[]{9, 4, 10, 11, 12},
                new int[]{11, 13},
        };
        EventPacker evtPcker = new EventPacker();
        Agenda result = evtPcker.getMaxAttendees(meetings);
        System.out.println("Meetings: " + result.toString());

        assertTrue(validateNoConflicts(result));
        assertEquals(13, result.attendeeCount);
    }

    @Test
    public void testMeetingsVeryLarge() {
        // Ideally need to optimise method, this takes way too long....
        int[][] meetings = new int[][]{
                new int[]{71,61,26},
                new int[]{72,75,76},
                new int[]{73,54,64,10,68},
                new int[]{88,56,69},
                new int[]{71,17,8},
                new int[]{18},
                new int[]{40,17,66,47,13},
                new int[]{40,62,52,88},
                new int[]{11,79,36,58,15},
                new int[]{17,67},
                new int[]{33},
                new int[]{38},
                new int[]{33},
                new int[]{3,4,5,55,59,60,61,82,18,85,26,48,50},
                new int[]{17,84},
                new int[]{74,83,43},
                new int[]{71,89,27},
                new int[]{11,79,46,58},
                new int[]{34,33},
                new int[]{71,75,35},
                new int[]{66,14},
                new int[]{72,33},
                new int[]{75,76},
                new int[]{19,77,27},
                new int[]{40,72,75,44,7,35,20},
                new int[]{18,34},
                new int[]{71,17,33,67},
                new int[]{29,86,65,78,87,22,23,47,16,28},
                new int[]{38,37,27},
                new int[]{30,18,6,52,85,55},
                new int[]{79,28,58},
                new int[]{17,88,69},
                new int[]{76,23},
                new int[]{39,32,75},
                new int[]{29,86,65,78,87,22,47,23,16,28},
                new int[]{11,79,58},
                new int[]{21,88,51},
                new int[]{38},
                new int[]{61,88},
                new int[]{3,18},
                new int[]{39,0,24,23},
                new int[]{33,70},
                new int[]{42,75,7,35,20,37,80},
                new int[]{38,1,49},
                new int[]{31,12,88},
                new int[]{39,40,43},
                new int[]{33,56},
                new int[]{33,25},
                new int[]{33},
                new int[]{2,56},
                new int[]{52,85},
                new int[]{52,9,45,57},
                new int[]{53,33},
                new int[]{66,27},
                new int[]{63,88},
                new int[]{81,41,42,7,20}
        };

        EventPacker evtPcker = new EventPacker();
        Agenda result = evtPcker.getMaxAttendees(meetings);
        //System.out.println("Meetings: " + result.toString());

        assertTrue(validateNoConflicts(result));
        assertEquals(66, result.attendeeCount);

    }

    private boolean validateNoConflicts(Agenda agenda) {
        Set<Integer> seen = new HashSet<>();
        for(int[] meeting :  agenda.meetings) {
            for(int attendee : meeting) {
                if(seen.contains(attendee)) {
                    return false;
                }

                seen.add(attendee);
            }
        }

        return true;
    }

}