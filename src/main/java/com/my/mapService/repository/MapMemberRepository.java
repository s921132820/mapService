package com.my.mapService.repository;

import com.my.mapService.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MapMemberRepository implements MemberRepository{
    // 전체 멤버를 저장할 맵을 선언
    public static Map<Long, Member> store = new HashMap<>();
    // 맵에 저장할 때 사용할 ID(키) 선언
    private static Long sequence = 1L;

    @Override
    public Member save(Member member) {
        member.setId(sequence);
        sequence ++;
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
//        Optional 은 결과가 있으면 돌려주고,
//        없으면 null 값을 돌려준다.
    }

    @Override
    public List<Member> findAll() {
        List<Member> memberList =
                new ArrayList<>(store.values());
        return memberList;
    }

    @Override
    public Optional<Member> findByName(String name) {
        Optional<Member> result = store
                .values()
                .stream()
                .filter(x -> x.getName().equals(name))
                .findAny();
        // findAny : 하나라도 있으면 값 찾아주고, 없으면 null 반환
        return result;

//        for (Long key : store.keySet()) {
//            if(store.get(key).getName().equals(name)){
//                Optional<Member> r = Optional.ofNullable(store.get(key));
//                return r;
//            }
//        }
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public Member updateById(Long memberId, Member member) {
        store.put(memberId, member);
        return store.get(memberId);
    }

    public void clearStore() {
        store.clear();
        sequence = 1L;
    }
}