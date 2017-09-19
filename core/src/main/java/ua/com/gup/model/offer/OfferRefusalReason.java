package ua.com.gup.model.offer;

/**
 * Reasons of the offer's refusal. It is used by moderators.
 *
 * @author Kobylyatskyy Alexander
 */
public enum OfferRefusalReason {
    PROFANITY,
    ADULT_CONTENT,
    MISMATCH_DESCRIBE,
    PROHIBITED_CONTENT,
    CUSTOM
}