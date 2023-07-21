package hellospring.hello1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {
    @Around("execution(* hellospring.hello..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
// AOP(Aspect Oriented Programming)
// - 어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점으로 나누어서 보고 그 관점을 기준으로 각각 모듈화하겠다는 것이다
// @Aspect 어노테이션으로 AOP 의 Advice 역할을 수행합니다.
// @Around("execution(* hellospring.hello..*(..))")
// hellospring.hello 패키지와 그 하위 패키지에 속한
// 모든 메서드들을 대상으로 AOP를 적용하겠다는 의미이다.

// JoinPoint - 사이사이의 모든 메서든

// .execute 메서드는 @Around 어노테이션에 정의된 포인트컷(Pointcut)에 해당하는 메서드들이 실행될 때 호출된다.

// execute 메서드는 다음과 같은 작업을 수행합니다 :
//  1. System.currentTimeMillis()를 사용하여 메서드 실행 시작 시간을 기록합니다.
//
//  2. joinPoint.toString() 을 사용하여 현재 실행되고 있는 메서드를 문자열로 표현하여 로깅한다.
//
//  3. joinPoint.proceed()를 호출하여 대상 메서드를 실행한다.
//
//  4. 메서드 실행이 완료된 후, System.currentTimeMillis()를 다시 사용하여 메서드 실행 종료 시간을 기록한다.
//
//  5. 실행 종료 시간에서 시작 시간을 빼서 메서드 실행 시간을 계산한다.
//
//  6. 메서드 실행 시간을 로깅(출력)합니다.

//TimeTraceAop 클래스는 hellospring.hello 패키지와 하위 패키지에 속한 모든 메서드들의 실행시간을 측정하고 이를 로그로 출력합니다
//이를 통해 애플리케이션의 성능 분석 및 모니터링에 도움이 될 수 있습니다.
