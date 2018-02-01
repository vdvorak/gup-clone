package ua.com.gup.common.security;

//use Function.java
@Deprecated()
public enum UserFunction {

    //MANAGER
    SEARCH_MANAGER_PROFILES("Поиск менеджеров","GET /api/managers/"),
    READ_MANAGER_PROFILE("Доступ к профилю менеджера","GET /api/managers/{managerPublicId}"),

    SEARCH_PROFILES_MANAGER("Поиск профилей(менеджер)","GET /api/managers/profiles"),
    READ_PROFILE_MANAGER("Получить профиль(менеджер) ","GET /api/managers/profiles/{profilePublicId}"),

    LINK_USER_TO_MANAGER("Привязать пользователя к менеджеру","POST /api/managers/profiles/{profilePublicId}/link/{managerPublicId}"),
    UNLINK_USER_TO_MANAGER("Отвязать пользователя от менеджера","POST /api/managers/profiles/{profilePublicId}/unlink/{managerPublicId}"),

    GET_MANAGER_USERS("Получить список профилей клиентов менеджера","GET /api/managers/{managerPublicId}/users"),
    GET_MANAGER_USER("Получить профиль клиента менеджера","GET /api/managers/{managerPublicId}/users/{userPublicId}"),
    UPDATE_PROFILE_CONTACT_INFO_MANAGER("Редактировать контактную информацию клиента менеджера ",
            "PUT /api/managers/{managerPublicId}/users/{userPublicId}/contactinfo"),

    SEARCH_OFFERS_MANAGER("Поиск объявлений(менеджер)","GET /api/managers/{managerPublicId}/users/{userPublicId}/offers"),
    READ_OFFER_MANAGER("Прочитать объявлений(менеджер)","GET /api/managers/{managerPublicId}/users/{userPublicId}/offers/{offerId}"),
    CLONE_OFFER("Клонировать объявление","GET /api/managers/{managerPublicId}/users/{fromUserPublicId}/offers/clone/{toUserPublicId}"),

    SEARCH_MANAGER_ACTIONS("Поиск напоминаний для менеджера ","GET /api/managers/{managerPublicId}/actions"),
    READ_MANAGER_ACTION("Получить напоминание","GET /api/managers/{managerPublicId}/actions/{actionId}"),
    CREATE_MANAGER_ACTION("Создать напоминание","POST /api/managers/{managerPublicId}/actions"),
    UPDATE_MANAGER_ACTION("Редактировать напоминание","PUT /api/managers/{managerPublicId}/actions/{actionId}"),
    DELETE_MANAGER_ACTION("Удалить напоминание","DELETE /api/managers/{managerPublicId}/actions/{actionId}"),

    //COMPLAINTS
    CREATE_COMPLAINT("Создать жалобу","POST /api/complaints"),
    UPDATE_COMPLAINT_STATUS("Редактировать статус жалобы","PUT api/complaints/{id}/status"),
    UPDATE_COMPLAINT_TYPE("Редактировать тип жалобы","PUT api/complaints/{id}/type"),
    UPDATE_COMPLAINT_TYPES("Редактировать типы жалобы","PUT api/complaints/{id}/types"),

    READ_COMPLAINT_TYPES("Получить список типов жалоб","GET /api/complaints/types"),
    READ_COMPLAINT_STATUSES("Получить список статусов жалоб","GET /api/complaints/statuses"),
    READ_COMPLAINT("Получит данные жалобы","GET /api/complaints/{id}"),
    READ_COMPLAINTS_BY_OFFER("Получит жалобы по объявлению","GET /api/complaints/offer/{offerId}"),
    SEARCH_COMPLAINTS_BY_INITIATOR("Получит жалобы по автору","GET /api/complaints/initiator/{initiatorId}"),
    SEARCH_COMPLAINTS_BY_STATUS("Получит жалобы по статусу","GET /api/complaints/status/{status}"),
    SEARCH_COMPLAINTS_BY_FILTER("Получит жалобы по фильтру","GET /api/complaints"),

    //CATEGORY ATTRIBUTE
    CREATE_CATEGORY_ATTRIBUTE("Создать атрибут категории","POST /api/category-attributes"),
    READ_CATEGORY_ATTRIBUTE("Получить атрибут категории","GET /api/category-attributes/{id}"),
    UPDATE_CATEGORY_ATTRIBUTE("Редактировать атрибут категории","PUT /api/category-attributes"),
    DELETE_CATEGORY_ATTRIBUTE("Удалить атрибут категории","DELETE /api/category-attributes/{id}"),
    READ_ALL_CATEGORY_ATTRIBUTES("Получить атрибуты категории","POST /api/category-attributes/"),

    //CATEGORY
    CREATE_CATEGORY("Создать категорию","POST /api/categories"),
    READ_CATEGORY("Получить категорию","GET /api/categories/{id}"),
    UPDATE_CATEGORY("Редактировать категорию","PUT /api/categories"),
    DELETE_CATEGORY("Удалить категорию","DELETE /api/categories"),
    READ_ALL_CATEGORIES("Получить список категорий","GET /api/categories/"),


    //OFFER
    CREATE_OFFER("Создать объявление","POST /api/offers"),
    READ_MY_OFFERS_BY_STATUS("Получить объявления пользователя по статусу","/api/offers/my/{status}"),
    READ_OFFER("Получить объявление","/api/offers/my/seo/{seoUrl}"),
    DELETE_OFFER("Удалить объявление","/api/offers/{id}"),
    UPDATE_OFFER_STATUS("Редеактировать статус объявления","/api/offers/{id}/status/{status}"),
    UPDATE_MODERATOR_OFFER("Редеактировать объявление(модератор)","/api/offers/moderator"), //moder admin
    READ_MODERATOR_OFFERS_BY_STATUS("Получить обявления по статусу(модератор)","/api/offers/moderator/{status}"),//moder
    READ_MODERATOR_OFFERS_BY_FILTER("Поиск обявлений по фильтру(модератор)","/api/offers/moderator"),
    READ_OFFERS_VIEW("Получить объявление(Администратор, менеджер, можератор)","/api/offers/view/{id}"),//ROLE_MODERATOR', 'ROLE_ADMIN', 'ROLE_MANAGER'

    READ_OFFER_IMAGE("Получить данные о картинке","GET /api/offers/{offerId}/images/{imageId}"),
    READ_OFFER_IMAGES("Получить список картинок для объявления","GET /api/offers/{offerId}/images"),
    CREATE_OFFER_IMAGE("Создать картинку","POST /api/offers/{offerId}/images"),
    DELETE_OFFER_IMAGE("Удалить картинку","DELETE /api/offers/{offerId}/images/{imageId}"),

    DICTIONARY_CREATE("Создать значение словаря","POST /api/translates/dictionaries/{locale}"),
    DICTIONARY_EDIT("Редактировать значение словаря","POST /api/translates/dictionaries/{locale}/{key}"),
    DICTIONARY_DELETE("Удалить значение словаря","DELETE /api/translates/dictionaries/{locale}/{key}"),


    //PROFILES API
    SEARCH_PROFILES_ADMIN("Поиск профилей(администратор)","POST /api/users/admin/profiles"),
    READ_PROFILE_ADMIN("Получить профиль(администратор)","POST /api/users/admin/profiles/{profilePublicId}"),
    CREATE_PROFILE_ADMIN("Создать профиль(администратор)","POST /api/users/admin/profiles"),
    UPDATE_PROFILE_ADMIN("Редактировать профиль(администратор)","PUT /api/users/admin/profiles/{profilePublicId}"),
    BAN_PROFILE("Заблокировать пользователя","PUT /api/users/admin/profiles/ban/{id}"),
    UNBAN_PROFILE("Разблокировать пользователя","PUT /api/users/admin/profiles/unban/{id}"),
    DELETE_PROFILE_IMAGE("Удалить аватарку","DELETE /api/users/admin/profiles/image/{id}"),





    READ_ALL_ROLES("Получить список ролей","GET /api/security/roles"),
    READ_ROLE("Получить даныые по роли","GET /api/security/roles/{role}"),
    CREATE_ROLE("Создать роль","POST /api/security/roles"),
    DELETE_ROLE("Удалить роль","DELETE /api/security/roles/{role}"),
    EDIT_ROLE("Редактировать роль","PUT /api/security/roles/{role}"),
    ADD_FUNCTIONS_TO_ROLE("Добавить финкции к роли","PUT /api/security/roles/{role}/functions"),
    DELETE_ROLE_FUNCTIONS("Удалить функции у роли","DELETE /api/security/roles/{role}/functions"),
    CREATE_FUNCTION("Создать фуекцию", "POST /api/security/functions"),

    UPDATE_OFFER_MODERATOR("Редактировать объявление(модератор)","PUT /api/offers/moderator"),





    UNKNOWN("");

    private String title;
    private String[] paths;

    UserFunction(String title, String... paths) {
        this.title = title;
        this.paths = paths;
    }

    public String getTitle() {
        return title;
    }

    public String[] getPaths() {
        return paths;
    }
}
