package com.cnrmall.springboot;

import com.cnrmall.springboot.controller.viewobject.UserVO;
import com.cnrmall.springboot.response.CommonReturnType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ModelAttribute;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

   /* public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(-1);
        ListNode head = p;
        while(l1!=null && l2!=null) {
            //如果l1的值小于l2的值，就将p.next指向l1
            //然后l1继续往后移动一位
            if(l1.val<=l2.val) {
                p.next=l1;
                l1=l1.next;
            } else {
                p.next=l2;
                l2=l2.next;
            }
            p=p.next;
        }
        //如果l1和l2不一样长，等遍历完后，将p的next指向没遍历完的链表即可
        //比如l1长度是3，1->2->3，l2长度是5 1->2->3->8->9
        //等循环结束时，l1就指向8->9，只要将p.next指向8->9即可
        p.next = (l1==null? l2:l1);
        return head.next;
    }*/




}
