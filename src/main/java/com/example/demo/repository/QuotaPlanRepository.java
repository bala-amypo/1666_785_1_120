public interface QuotaPlanRepository {
    Optional<QuotaPlan> findById(Long id);
    List<QuotaPlan> findAll();
    QuotaPlan save(QuotaPlan p);
}
