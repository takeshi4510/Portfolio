package jp.co.tk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.tk.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	//NAMEとPASSをもとにUSERTABLEからデータを取得
	@Query(value="select * from USER where NAME = ?1 and PASS = ?2"
			, nativeQuery=true)
	public List<UserEntity> findbyUserAndPassword(String name, String password);

	//NAMEをもとにUSERTABLEからデータを取得
	@Query(value="select * from USER where NAME = ?1"
			, nativeQuery= true)
	public List<UserEntity> findbyUser(String name);

	//NAMEとPASSをもとにUSERTABLEからデータを取得
	@Query(value="select ID from USER where NAME = ?1 and PASS = ?2"
			, nativeQuery=true)
	public int findById(String name, String password);

	//IDをもとにUSERTABLEからデータを取得
	@Query(value="select NAME from USER where ID = ?1"
			,nativeQuery=true)
	public String findUserNameById(Object userId);


}