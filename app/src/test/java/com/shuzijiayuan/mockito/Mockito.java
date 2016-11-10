package com.shuzijiayuan.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by gc on 16/10/13.
 */
@RunWith(MockitoJUnitRunner.class)
public class Mockito {

    /**
     * 验证某些行为
     * mock(T): 创建对象
     * verify(T): 验证行为
     */
    @Test
    public void verifyMockito() {
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    /**
     * 测试桩 (Stub)
     * <p>
     * 1.默认情况下，所有的函数都有返回值。mock函数默认返回的是null，一个空的集合或者一个被对象类型包装的内置类型，例如0、false对应的对象类型为Integer、Boolean；
     * 2.测试桩函数可以被覆写 : 例如常见的测试桩函数可以用于初始化夹具，但是测试函数能够覆写它。请注意，覆写测试桩函数是一种可能存在潜在问题的做法；
     * 3.一旦测试桩函数被调用，该函数将会一致返回固定的值；
     * 4.上一次调用测试桩函数有时候极为重要-当你调用一个函数很多次时，最后一次调用可能是你所感兴趣的。
     */
    @Test
    public void testStub() {
        // 你可以mock具体的类型,不仅只是接口
        LinkedList mockedList = mock(LinkedList.class);

        // 测试桩
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        // 输出“first”
        System.out.println(mockedList.get(0));

        // 抛出异常
//        System.out.println(mockedList.get(1));

        // 因为get(999) 没有打桩，因此输出null
        System.out.println(mockedList.get(999));

        // 验证get(0)是否被调用
        verify(mockedList).get(0);
    }

    /**
     * 参数匹配器 (matchers)
     */
    @Test
    public void matchers() {
        LinkedList mockedList = mock(LinkedList.class);

        // 使用内置的anyInt()参数匹配器
        when(mockedList.get(anyInt())).thenReturn("element");

        // 输出element
        System.out.println(mockedList.get(999));

        // 你也可以验证参数匹配器
        verify(mockedList).get(anyInt());
    }

    /**
     * 验证函数的确切、最少、从未调用次数
     */
    @Test
    public void count() {
        LinkedList mockedList = mock(LinkedList.class);

        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        // 下面的两个验证函数效果一样,因为verify默认验证的就是times(1)
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        // 验证具体的执行次数
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        // 使用never()进行验证,never相当于times(0)
        verify(mockedList, never()).add("never happened");

        //最后一次添加
        verify(mockedList, atLeastOnce()).add("three times");

        //最少添加2次
//        verify(mockedList, atLeast(2)).add("five times");

        //最多添加5次
        verify(mockedList, atMost(5)).add("three times");
    }

    /**
     * 为返回值为void的函数通过Stub抛出异常
     */
    @Test
    public void testDoThrow() {
        LinkedList mockedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
//        mockedList.clear();
    }

    /**
     * 验证执行执行顺序
     * 验证执行顺序是非常灵活的-你不需要一个一个的验证所有交互,只需要验证你感兴趣的对象即可。
     * 另外，你可以仅通过那些需要验证顺序的mock对象来创建InOrder对象。
     */
    @Test
    public void testOrder() {
        LinkedList singleMock = mock(LinkedList.class);

        singleMock.add("was added first");
        singleMock.add("was added second");

        // 为该mock对象创建一个inOrder对象
        InOrder inOrder = inOrder(singleMock);

        // 确保add函数首先执行的是add("was added first"),然后才是add("was added second")
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        // B .验证多个mock对象的函数执行顺序
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        // 为这两个Mock对象创建inOrder对象
        InOrder inOrders = inOrder(firstMock, secondMock);

        // 验证它们的执行顺序
        inOrders.verify(firstMock).add("was called first");
        inOrders.verify(secondMock).add("was called second");
    }

    /**
     * 确保交互(interaction)操作不会执行在mock对象上
     */
    @Test
    public void verifyInteraction() {
        LinkedList mockOne = mock(LinkedList.class);
        LinkedList mockTwo = mock(LinkedList.class);

        mockOne.add("one");

        verify(mockOne).add("one");

        verify(mockOne, never()).add("two");

        // 验证mock对象没有交互过
        verifyZeroInteractions(mockTwo);
    }

    /**
     * 查找冗余的调用
     * <p>
     * 一些用户可能会在频繁地使用 verifyNoMoreInteractions() ，甚至在每个测试函数中都用。
     * 但是 verifyNoMoreInteractions() 并不建议在每个测试函数中都使用。
     * verifyNoMoreInteractions() 在交互测试套件中只是一个便利的验证，它的作用是当你需要验证是否存在冗余调用时。
     * 滥用它将导致测试代码的可维护性降低。
     * <p>
     * never() 是一种更为明显且易于理解的形式。
     */
    @Test
    public void verifyNoMore() {
        LinkedList mockOne = mock(LinkedList.class);

        mockOne.add("one");
//        mockOne.add("two");

        verify(mockOne).add("one");

        //如调用未被验证则报错
//        verifyNoMoreInteractions(mockOne);
    }

    /**
     * 为连续的调用做测试桩 (stub)
     */
    @Test
    public void testStub2() {
        LinkedList mockOne = mock(LinkedList.class);

        when(mockOne.getLast()).thenReturn("one", "two", "three");

        mockOne.getLast();

        System.out.println(mockOne.getLast());

        System.out.println(mockOne.getLast());
    }

    /**
     * 为回调做测试桩
     */
    @Test
    public void testStubForCallback() {
        LinkedList mockOne = mock(LinkedList.class);
        when(mockOne.get(anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "called with arguments: " + args[0];
            }
        });

        // 输出 : "called with arguments: 10"
        System.out.println(mockOne.get(10));
    }

    /**
     * 监控真实对象
     */
    @Test
    public void testSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        // 你可以为某些函数打桩
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        // 通过spy对象调用真实对象的函数
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        // 输出第一个元素
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        // 因为size()函数被打桩了,因此这里返回的是100
        System.out.println(spy.size());

        //optionally, you can verify
        // 交互验证
        verify(spy).add("one");
        verify(spy).add("two");

    }

    /**
     * 监控真实对象
     */
    @Test
    public void testSpyReturn() {
        List list = new LinkedList();
        List spy = spy(list);

        // 不可能 : 因为当调用spy.get(0)时会调用真实对象的get(0)函数,此时会发生IndexOutOfBoundsException异常，因为真实List对象是空的
//        when(spy.get(0)).thenReturn("foo");

        // 你需要使用doReturn()来打桩
        doReturn("foo").when(spy).get(0);
    }
}
