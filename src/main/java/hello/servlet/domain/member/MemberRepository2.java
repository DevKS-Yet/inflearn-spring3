package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemberRepository2 {

    private static ConcurrentHashMap<Long, Member> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(0L);

    private static final MemberRepository2 instance = new MemberRepository2();

    public static MemberRepository2 getInstance() {
        return instance;
    }

    public MemberRepository2() {
    }

    public Member save(Member member) {
        member.setId(sequence.longValue());
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
