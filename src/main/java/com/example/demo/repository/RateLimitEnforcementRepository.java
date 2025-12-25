public interface RateLimitEnforcementRepository {
    Optional<RateLimitEnforcement> findById(Long id);
    List<RateLimitEnforcement> findByApiKey_Id(Long id);
    RateLimitEnforcement save(RateLimitEnforcement e);
}
