package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//** interface JpaRepository<T, ID>
//=> interface 계층도
// Repository<T, ID> -> CrudRepository<T, ID> 
// -> PagingAndSortingRepository<T, ID> -> JpaRepository<T, ID>

//=> CrudRepository<T, ID> : 기본기능만 사용하는 경우 상속받음
//=> JpaRepository<T, ID> : JPA 관련기능 대부분 사용하는경우 상속받음

//=> 이들을 상속받는것만으로도 모든 처리가 됨.
//=> Test Code 로 확인가능

import com.example.demo.entity.GuestBook;

public interface GuestBookRepository 
					extends JpaRepository<GuestBook, Long> {
}
