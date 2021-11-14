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



	@Query(value = "select * from BBS order by ID desc"
			, nativeQuery = true)
	public List<BbsHomeEntity> findAllDesc();


	@Query(value = "select * from BBS where ID = ?1"
			, nativeQuery = true)
	public List<BbsHomeEntity> findByBbsId(Integer id);


	@Query(value = "select * from USER where NAME = ?1"
			, nativeQuery = true)
	public int IdSelect(String name);

	@Transactional
	@Modifying
	@Query(value = "delete from BBS where ID = ?1"
	, nativeQuery = true)
	public int remove(String user_id);


	//bbs編集IDを取得
	@Query(value = "select * from BBS where ID = ?1"
			, nativeQuery = true)
	public Optional<BbsHomeEntity> edit(String bbs_id);

}
