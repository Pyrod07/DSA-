class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null)return head;
        
        ListNode curr=head, pre=null;

        while(curr!=null){
            ListNode temp=curr.next;
            curr.next=pre;
            pre=curr;
            curr=temp;
        }
        return pre;
        
    }
}