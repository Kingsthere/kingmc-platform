package kingmc.platform.facet

import kingmc.platform.PlatformImplementation

/**
 * Indicating a part of facet implementation that implemented platform api
 * 
 * @author kingsthere
 * @since 0.0.6
 */
@PlatformImplementation
@Target(AnnotationTarget.CLASS)
@Retention
annotation class FacetImplementation
