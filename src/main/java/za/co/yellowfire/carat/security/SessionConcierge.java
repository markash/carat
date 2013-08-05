package za.co.yellowfire.carat.security;

import lombok.extern.slf4j.Slf4j;

/**
 * http://blog.eisele.net/2012/03/java-ee-6-example-enhance-security-with.html
 */
@Slf4j
public final class SessionConcierge {
//    public static boolean addSession(HttpSession session) {
//        String account = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
//        String sessionId = session.getId();
//        if (account != null && !getApplicationMap(session).containsKey(account)) {
//            getApplicationMap(session).put(account, sessionId);
//            if (log.isDebugEnabled()) {
//                log.debug("Added Session with ID {} for user {}", sessionId, account);
//            }
//            return true;
//        } else {
//            log.error("Cannot add sessionId, because current logged in account is NULL or session already assigned!");
//            return false;
//        }
//    }
//
//    public static void removeSession(HttpSession session) {
//        String sessionId = session.getId();
//        String account = getKeyByValue(getApplicationMap(session), sessionId);
//        if (account != null) {
//            getApplicationMap(session).remove(account);
//            if (log.isDebugEnabled()) {
//                log.debug("Removed Session with ID {} for user {}", sessionId, account);
//            }
//        }
//    }
//
//    private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
//        for (Map.Entry<T, E> entry : map.entrySet()) {
//            if (value.equals(entry.getValue())) {
//                return entry.getKey();
//            }
//        }
//        return null;
//    }
}
