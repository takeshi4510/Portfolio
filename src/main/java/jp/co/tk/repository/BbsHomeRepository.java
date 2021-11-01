package jp.co.tk.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.tk.entity.BbsHomeEntity;

@Mapper
@Repository
public interface BbsHomeRepository extends JpaRepository<BbsHomeEntity, Long>{



	@Query(value = "select * from bbs"
			, nativeQuery = true)
	public List<BbsHomeEntity> findBySelectAll();


	@Query(value = "select * from bbs where id = ?1"
			, nativeQuery = true)
	public List<BbsHomeEntity> findbyId(Integer id);


	@Query(value = "select * from user where name = ?1"
			, nativeQuery = true)
	public int IdSelect(String name);

	@Transactional
	@Modifying
	@Query(value = "delete from bbs where id = ?1"
	, nativeQuery = true)
	public int remove(String user_id);


	//bbs編集IDを取得
	@Query(value = "select * from bbs where id = ?1"
			, nativeQuery = true)
	public Optional<BbsHomeEntity> edit(String bbs_id);

}
