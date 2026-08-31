/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] result = new int[]{-1, -1};
        
        if (head == null || head.next == null || head.next.next == null) {
            return result;
        }

        ListNode prev = head;
        ListNode curr = head.next;
        int position = 1;
        int firstCritical = -1;
        int previousCritical = -1;
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = 0;

        while (curr.next != null) {

            ListNode next = curr.next;

            boolean isCritical =
                    (curr.val > prev.val && curr.val > next.val) ||
                    (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {

                if (firstCritical == -1) {
                    firstCritical = position;
                }

                if (previousCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        position - previousCritical
                    );
                }

                previousCritical = position;
            }

            prev = curr;
            curr = curr.next;
            position++;
        }

        if (firstCritical == previousCritical) {
            return result;
        }

        maxDistance = previousCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}