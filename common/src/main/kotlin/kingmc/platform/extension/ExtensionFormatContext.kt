package kingmc.platform.extension

import kingmc.util.format.FormatArgument
import kingmc.util.format.FormatContext

class ExtensionFormatContext : FormatContext, List<FormatArgument<*>> by mutableListOf()