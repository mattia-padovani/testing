import java.util.*;

public class Agenda {

    public int attendeeCount = 0;
    public List<int[]> meetings = new ArrayList<>();
    Map<Integer,Integer> seen = new HashMap<>();

    public Agenda() {}

    public Agenda(Agenda m) {
        meetings = new LinkedList<>(m.meetings);
        seen = new HashMap<>(m.seen);
        attendeeCount = m.attendeeCount;
    }

    public void addMeeting(int[] meeting) {
        Arrays.stream(meeting).forEach( attendee -> seen.put( attendee, meetings.size() ));
        attendeeCount += meeting.length;
        meetings.add(meeting);
    }

    public void replaceMeeting(Integer dupIndex, int[] meeting) {
        int[] removed = meetings.get(dupIndex);
        Arrays.stream(removed).forEach(attendee -> seen.remove(attendee));
        attendeeCount -= removed.length;

        meetings.set(dupIndex, meeting);
        attendeeCount += meeting.length;
        Arrays.stream(meeting).forEach( attendee -> seen.put( attendee, dupIndex ));
    }

    public int hasDuplicateAttendee(int[] meeting) {
        for(int attendee : meeting) {
            if(seen.containsKey(attendee)) return seen.get(attendee);
        }

        return -1;
    }

    public void removeLastMeeting() {
        int[] removed = meetings.remove(meetings.size()-1);
        Arrays.stream(removed).forEach(attendee -> seen.remove(attendee));
        attendeeCount -= removed.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int[] meeting : meetings) {
            sb.append(" [");
            sb.append(Arrays.toString(meeting));
            sb.append(" ] ");
        }

        return sb.toString();
    }
}
