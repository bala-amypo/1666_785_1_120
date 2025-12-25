public interface ApiKeyRepository {
    Optional<ApiKey> findById(Long id);
    Optional<ApiKey> findByKeyValue(String keyValue);
    List<ApiKey> findAll();
    ApiKey save(ApiKey k);
}
