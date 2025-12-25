public interface KeyExemptionRepository {
    Optional<KeyExemption> findByApiKey_Id(Long id);
    Iterable<KeyExemption> findAll();
    KeyExemption save(KeyExemption e);
    void delete(KeyExemption e);
}
