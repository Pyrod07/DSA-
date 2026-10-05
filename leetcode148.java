class Solution {
    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        // Find length of the linked list
        int n = 0;
        ListNode curr = head;

        while (curr != null) {
            n++;
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // size = length of each sublist being merged
        for (int size = 1; size < n; size *= 2) {

            curr = dummy.next;
            ListNode tail = dummy;

            while (curr != null) {

                // First half
                ListNode left = curr;

                // Second half
                ListNode right = split(left, size);

                // Next unsorted portion
                curr = split(right, size);

                // Merge left and right
                ListNode merged = merge(left, right);

                // Connect merged part
                tail.next = merged;

                // Move tail to the end of merged list
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }

        return dummy.next;
    }

    // Split first 'size' nodes and return the remaining list
    private ListNode split(ListNode head, int size) {

        if (head == null) {
            return null;
        }

        ListNode curr = head;

        for (int i = 1; i < size && curr.next != null; i++) {
            curr = curr.next;
        }

        ListNode next = curr.next;
        curr.next = null;

        return next;
    }

    // Merge two sorted linked lists
    private ListNode merge(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (left != null && right != null) {

            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }

            tail = tail.next;
        }

        if (left != null) {
            tail.next = left;
        } else {
            tail.next = right;
        }

        return dummy.next;
    }
}