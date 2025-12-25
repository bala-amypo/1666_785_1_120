public interface ApiUsageLogRepository {
    ApiUsageLog save(ApiUsageLog log);
    List<ApiUsageLog> findByApiKey_Id(Long id);
    List<ApiUsageLog> findForKeyBetween(Long id, Instant s, Instant e);
    int countForKeyBetween(Long id, Instant s, Instant e);
}
