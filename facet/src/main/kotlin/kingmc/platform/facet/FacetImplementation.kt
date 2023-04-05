package kingmc.platform.facet

import kingmc.platform.PlatformImplementation

/**
 * Indicating a part of facet implementation that implemented platform api
 * 
 * @since 0.0.6
 * @author kingsthere
 */
@PlatformImplementation
@Target(AnnotationTarget.CLASS)
@Retention
annotation class FacetImplementation
