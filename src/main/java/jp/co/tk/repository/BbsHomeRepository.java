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


	//BBSTABLEのデータを全件取得
	@Query(value = "select * from BBS order by ID desc"
			, nativeQuery = true)
	public List<BbsHomeEntity> findAllDesc();

	//IDをもとにBBSTABLEのデータを取得
	@Query(value = "select * from BBS where ID = ?1"
			, nativeQuery = true)
	public List<BbsHomeEntity> findByBbsId(Integer id);

	//BBSTABLEのデータをIDをもとにデータを削除
	@Transactional
	@Modifying
	@Query(value = "delete from BBS where ID = ?1"
	, nativeQuery = true)
	public int removeBbs(String user_id);

	//IDをもとにBBSTABLEからデータを取得
	@Query(value = "select * from BBS where ID = ?1"
			, nativeQuery = true)
	public Optional<BbsHomeEntity> updateBbs(String bbs_id);

}
