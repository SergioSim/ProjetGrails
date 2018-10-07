

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'mbds.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'mbds.UserRole'
grails.plugin.springsecurity.authority.className = 'mbds.Role'
grails.plugin.springsecurity.requestMap.className = 'mbds.UserRole'
grails.plugin.springsecurity.securityConfigType = 'Annotation'
grails.plugin.springsecurity.rest.token.storage.jwt.secret = 'qrD6h8K6S9503Q06Y6Rfk21TErImPYqa'
grails.plugin.springsecurity.rest.token.storage.useGorm = true
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'mbds.AuthenticationToken'
grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/user/**',        access: ['ROLE_ADMIN']],
	[pattern: '/message/**',     access: ['ROLE_ADMIN']],
	[pattern: '/role/**',        access: ['ROLE_ADMIN']],
	[pattern: '/userRole/**',    access: ['ROLE_ADMIN']],
	[pattern: '/userHome/**',    access: ['ROLE_ADMIN']],
	[pattern: '/deadMatch/**',   access: ['ROLE_ADMIN']],
	[pattern: '/userImage/**',   access: ['ROLE_ADMIN']],
	[pattern: '/userHome/index', access: ['ROLE_ADMIN','ROLE_USER']],
    [pattern: '/api/login',      access: ['permitAll']],
    [pattern: '/api/logout',     access: ['isAuthenticated()']],
    [pattern: '/api/user/**',    access: ['isAuthenticated()']],
	[pattern: '/api/message/**', access: ['isAuthenticated()']],
	[pattern: '/api/deadmatch/**',access: ['isAuthenticated()']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
    [pattern: '/api/**',         filters: 'JOINED_FILTERS,' +
                                        '-anonymousAuthenticationFilter,' +
                                        '-exceptionTranslationFilter,' +
                                        '-authenticationProcessingFilter,' +
                                        '-securityContextPersistenceFilter,' +
                                        '-rememberMeAuthenticationFilter'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.logout.postOnly = false